package com.hashmap.models.auction;

import java.math.BigDecimal;
import java.util.UUID;

public class Auction {

    private UUID auctionId;
    private UUID sellerId;
    private Item item;
    private int endTimeInSeconds;
    private boolean isAuctionOpen;
    private Bid currentBid;
    final private BigDecimal openingAuctionPrice;
   // private TimerService countDownTimer;


    public Auction(Item item, UUID sellerId, BigDecimal openingAuctionPrice, int endTimeInSecond) {
        this.auctionId = UUID.randomUUID();
        this.item = item;
        this.sellerId = sellerId;
        this.openingAuctionPrice = openingAuctionPrice;
        this.endTimeInSeconds = endTimeInSecond;
        isAuctionOpen = true;
        currentBid = new Bid(sellerId,openingAuctionPrice);
      //  countDownTimer = new TimerService(endTimeInSeconds, this.auctionId);
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

    public BigDecimal getOpeningAuctionPrice() {
        return openingAuctionPrice;
    }

    public int getEndTimeInSeconds() {
        return endTimeInSeconds;
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

    public UUID getSellerId() {
        return sellerId;
    }
}
