package com.hashmap.models;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class AuctionTest {

    @Test
    public void Auction_fields_ShouldNotNull() {
        Auction auction;
        auction = new Auction(new Item("jet engines", "mach 3 speed"),
                new Bid(UUID.randomUUID(), new BigDecimal(300)), 3);

        Assert.assertNotNull(auction.getAuctionId());
        Assert.assertNotNull(auction.getOpeningBid());

        Assert.assertNotNull(auction.getItem().getName());
        Assert.assertNotNull(auction.getItem().getDescription());

        //Assert.assertEquals(auction.getCurrentBid(),null);
    }
}
