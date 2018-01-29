package com.hashmap.models;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class BidTest {

    @Test
    public void Bid_Fields_ShouldNotNull() {

        Bid bid = new Bid(UUID.randomUUID(),new BigDecimal(100));

        Assert.assertNotNull(bid.getUserId());
        Assert.assertNotNull(bid.getBidPrice());

    }
}
