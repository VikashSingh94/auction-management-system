package com.hashmap.models.auction;

import java.math.BigDecimal;
import java.util.UUID;

public class Bid {
    private UUID userId;
    private BigDecimal bidPrice;

    public Bid(UUID userId, BigDecimal bidPrice) {
        this.userId = userId;
        this.bidPrice = bidPrice;
    }

    public UUID getUserId() {
        return userId;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }
}
