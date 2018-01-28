package com.hashmap.models;

import java.util.UUID;

public class Auction {

    private UUID auctionId;
    private Item item;
    private int endTimeInSecond;
    private boolean isAuctionOpen;
    private Bid currentBid;
    final private Bid openingBid;
    private CountDownTimer countDownTimer;


    public Auction(Item item, Bid openingBid, int endTimeInSecond) {
        this.auctionId = UUID.randomUUID();
        this.item = item;
        this.openingBid = openingBid;
        this.endTimeInSecond = endTimeInSecond;
        isAuctionOpen = true;
        currentBid = null;
        countDownTimer = new CountDownTimer(endTimeInSecond, this.auctionId);
    }

    public void setCurrentBid(Bid currentBid) {
        this.currentBid = currentBid;
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public Item getItem() {
        return item;
    }

    public Bid getOpeningBid() {
        return openingBid;
    }

    public int getEndTimeInSecond() {
        return endTimeInSecond;
    }

    public boolean getIsAuctionOpen() {
        return isAuctionOpen;
    }

    public void setAuctionOpen(boolean isAuctionOpen) {
        this.isAuctionOpen = isAuctionOpen;
    }

    public Bid getCurrentBid() {
        return currentBid;
    }
}
