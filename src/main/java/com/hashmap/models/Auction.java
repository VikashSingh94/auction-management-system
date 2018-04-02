package com.hashmap.models;


import java.math.BigDecimal;
import java.util.UUID;


public class Auction {

    private UUID auctionId;
    private UUID userId;
    private Item item;
    private BigDecimal openingAuctionPrice;
    private int endTime;
    private boolean isAuctionOpen;
    private Bid currentBid;
    public Auction(){
        this.isAuctionOpen = false;
//        this.currentBid = null;
    }


    public Auction(Item item, UUID sellerId, BigDecimal openingAuctionPrice, int endTime) {
        this.item = item;
        this.userId = sellerId;
        this.openingAuctionPrice = openingAuctionPrice;
        this.endTime = endTime;
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(UUID auctionId) {
        this.auctionId = auctionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getOpeningAuctionPrice() {
        return openingAuctionPrice;
    }

    public void setOpeningAuctionPrice(BigDecimal openingAuctionPrice) {
        this.openingAuctionPrice = openingAuctionPrice;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public boolean isAuctionOpen() {
        return isAuctionOpen;
    }

    public void setAuctionOpen(boolean auctionOpen) {
        isAuctionOpen = auctionOpen;
    }

    public Bid getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Bid currentBid) {
        this.currentBid = currentBid;
    }
}
