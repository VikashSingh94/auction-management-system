package com.hashmap.service;


import com.hashmap.dao.InMemoryDAOImpl;
import com.hashmap.dao.InMemoryDoa;
import com.hashmap.models.auction.Auction;

import java.math.BigDecimal;
import java.util.UUID;

interface Listener
{
    void onAuctionEnd();
    void paymentProcess();
}

class AuctionListener implements Listener {

    UUID auctionId;
    InMemoryDoa inMemoryDoa;
    PaymentGateWay paymentGateWay;

    public AuctionListener(UUID auctionId)
    {
        this.auctionId = auctionId;
        inMemoryDoa = new InMemoryDAOImpl();
        paymentGateWay = new PaymentGateWayImpl();
    }

    @Override
    public void onAuctionEnd()
    {
        inMemoryDoa.updateIsAuctionOpen(this.auctionId, false);
    }

    @Override
    public void paymentProcess() {

        Auction auction = inMemoryDoa.getAuction(this.auctionId);

        UUID payerId = auction.getCurrentBid().getUserId();
        UUID payeeId = auction.getSellerId();
        BigDecimal paymentAmount = auction.getCurrentBid().getBidPrice();

        paymentGateWay.pay(payerId,payeeId,paymentAmount);
    }

}


