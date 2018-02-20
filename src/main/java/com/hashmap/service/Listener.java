package com.hashmap.service;


import com.hashmap.dao.InMemoryDAOImpl;
import com.hashmap.dao.InMemoryDoa;
import com.hashmap.exception.InvalidAuction;
import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;

import java.math.BigDecimal;
import java.util.UUID;

interface Listener {
    void onAuctionEnd();
    void paymentProcess();
}

class AuctionListener implements Listener {

    private UUID auctionId;
    private InMemoryDoa inMemoryDoa;
    private PaymentGateWay paymentGateWay;

    public AuctionListener(UUID auctionId) {
        this.auctionId = auctionId;
        inMemoryDoa = new InMemoryDAOImpl();
        paymentGateWay = new PaymentGateWayImpl();
    }

    @Override
    public void onAuctionEnd() {
        inMemoryDoa.updateIsAuctionOpen(this.auctionId, false);
    }

    @Override
    public void paymentProcess() {

        try {

            Auction auction = inMemoryDoa.getAuction(this.auctionId);

            Bid currentBid = auction.getCurrentBid();
            UUID buyerId = currentBid.getUserId();

            if (buyerId != auction.getSellerId()) {

                UUID payerId = auction.getCurrentBid().getUserId();
                UUID payeeId = auction.getSellerId();

                BigDecimal paymentAmount = auction.getCurrentBid().getBidPrice();

                paymentGateWay.pay(payerId, payeeId, paymentAmount);
            }
        }
        catch (InvalidAuction invalidAuction) {
            return;
        }

    }

}


