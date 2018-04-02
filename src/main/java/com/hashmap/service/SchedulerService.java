


package com.hashmap.service;


import com.hashmap.dao.AuctionDao;
import com.hashmap.models.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
class SchedulerService {


    @Autowired
    private AuctionDao auctionDao;
    @Autowired
    private WalletService walletService;

    SchedulerService() {

    }


    void startTimer(int seconds, UUID auctionId) {

        seconds = (seconds * 1000);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        endAuction(auctionId);
                        paymentProcess(auctionId);
                    }
                }, seconds

        );
    }

    private void endAuction(UUID auctionId) {
        Optional<Auction> auction = auctionDao.getAuction(auctionId);

        if (auction.isPresent()) {
            auction.get().setAuctionOpen(false);
            auctionDao.update(auction.get());
        }

    }


    private void paymentProcess(UUID auctionId) {

        Optional<Auction> optionalAuction = auctionDao.getAuction(auctionId);

        if (optionalAuction.isPresent()) {

            Auction auction = optionalAuction.get();

            if(auction.getCurrentBid() != null) {
                UUID payerId = auction.getCurrentBid().getUserId();
                UUID payeeId = auction.getUserId();
                BigDecimal paymentAmount = auction.getCurrentBid().getBidPrice();
                walletService.pay(payerId, payeeId, paymentAmount);
            }
        }
    }

}
