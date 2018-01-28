package com.hashmap.models;

import java.math.BigDecimal;
import java.util.UUID;

public class Auction {

    private UUID auctionid;
    private Item item;
    private BigDecimal startingAuctionPrice;
    private int endTimeInSecond;
    private boolean isAuctionOpen;
    private CountDownTimer countDownTimer;


    public Auction(Item item, final BigDecimal startingAuctionPrice, int endTimeInSecond) {
        this.auctionid = UUID.randomUUID();
        this.item = item;
        this.startingAuctionPrice = startingAuctionPrice;
        this.endTimeInSecond = endTimeInSecond;
        isAuctionOpen = true;
        countDownTimer = new CountDownTimer(endTimeInSecond, this.auctionid);
    }


    public UUID getAuctionid() {
        return auctionid;
    }


    public Item getItem() {
        return item;
    }

    public BigDecimal getStartingAuctionPrice() {
        return startingAuctionPrice;
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
}
