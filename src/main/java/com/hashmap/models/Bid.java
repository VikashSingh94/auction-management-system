package com.hashmap.models;

import java.math.BigDecimal;
import java.util.UUID;

public class Bid {
    private UUID auctionId;
    private UUID userId;
    private BigDecimal bidPrice;

    public Bid(UUID auctionId, UUID userId, BigDecimal bidPrice) {
        this.auctionId = auctionId;
        this.userId = userId;
        this.bidPrice = bidPrice;
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }
}
