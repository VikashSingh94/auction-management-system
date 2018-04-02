package com.hashmap.models;


import java.math.BigDecimal;
import java.util.UUID;

public class Bid {

    private UUID bidId;
    private UUID userId;
    private UUID auctionId;
    private BigDecimal bidPrice;

    public Bid(){

    }

    public Bid( UUID userId, UUID auctionId, BigDecimal bidPrice) {
        this.userId = userId;
        this.auctionId = auctionId;
        this.bidPrice = bidPrice;
    }

    public Bid( UUID userId,BigDecimal bidPrice) {
        this.userId = userId;
        this.bidPrice = bidPrice;
    }


    public UUID getBidId() {
        return bidId;
    }

    public void setBidId(UUID bidId) {
        this.bidId = bidId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(UUID auctionId) {
        this.auctionId = auctionId;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }
}
