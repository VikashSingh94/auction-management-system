package com.hashmap.models;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class AuctionTest {

    @Test
    public void testCreateAuction() {
        Auction auction;
        auction = new Auction(new Item("jet engines","mach 3 speed"),
                    new Bid(UUID.randomUUID(),new BigDecimal(300)),1);

        Assert.assertNotNull(auction.getAuctionId());
        Assert.assertNotNull(auction.getOpeningBid());
        Assert.assertNotNull(auction.getIsAuctionOpen());
        Assert.assertNotNull(auction.getEndTimeInSecond());
        Assert.assertNotNull(auction.getItem().getName());
        Assert.assertNotNull(auction.getItem().getDescription());
    }

}
