package com.hashmap.service;

import com.hashmap.dao.AuctionDao;
import com.hashmap.dao.BidDao;
import com.hashmap.exception.InsufficientFundsException;
import com.hashmap.exception.InvalidAuctionException;
import com.hashmap.exception.InvalidBidException;
import com.hashmap.models.Auction;
import com.hashmap.models.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class BidService {

    @Autowired
    private WalletService walletService;

    @Autowired
    private BidDao bidDao;

    @Autowired
    private AuctionDao auctionDao;

    BidService() {
    }


   public Bid placeBid(UUID auctionId, Bid bid ){

        bid.setAuctionId(auctionId);

       checkBidCanBePlaced(bid);

       bid = bidDao.save(bid);

       updateCurrentBid(auctionId, bid);

       return bid;
    }

    private void checkBidCanBePlaced(Bid bid){

        Optional<Auction> auction = auctionDao.getAuction(bid.getAuctionId());

        if (auction.isPresent()) {

            if(!auction.get().isAuctionOpen())
                throw new InvalidAuctionException("Auction is closed now ");

            if(!isSufficientBalance(bid))
                throw new InsufficientFundsException("not sufficient balance");

            if(!isPresentBidGreaterThenCurrentBid(auction.get(), bid))
                throw new InvalidBidException("Bid price is lower than the current bidEntity");

        }
        else
            throw new InvalidAuctionException("Auction is not present ");
    }

    private boolean isSufficientBalance(Bid bid) {
        UUID userId = bid.getUserId();
        BigDecimal bidPrice = bid.getBidPrice();

        return walletService.checkSufficientBalance(userId, bidPrice);
    }

    private boolean isPresentBidGreaterThenCurrentBid(Auction auction, Bid bid) {

        if(auction.getCurrentBid() == null)
            return true;

        BigDecimal currentBidPrice = auction.getCurrentBid().getBidPrice();
        BigDecimal presentBidPrice = bid.getBidPrice();

        return presentBidPrice.compareTo(currentBidPrice) >= 0;
    }

    private Auction updateCurrentBid(UUID auctionId, Bid bid) {
        Optional<Auction> auction = auctionDao.getAuction(bid.getAuctionId());

        auction.get().setCurrentBid(bid);
        return auctionDao.update(auction.get());
    }
}
