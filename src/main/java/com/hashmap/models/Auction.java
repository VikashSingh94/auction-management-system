package com.hashmap.models;

import java.math.BigDecimal;
import java.util.UUID;

public class Auction {

    private UUID id;
    private Item item;
    private BigDecimal startingAuctionPrice;

    public Auction(Item item, BigDecimal startingAuctionPrice) {
        this.id = UUID.randomUUID();
        this.item = item;
        this.startingAuctionPrice = startingAuctionPrice;
    }


    public UUID getId() {
        return id;
    }


    public Item getItem() {
        return item;
    }

    public BigDecimal getStartingAuctionPrice() {
        return startingAuctionPrice;
    }
}
