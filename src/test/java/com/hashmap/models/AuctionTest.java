package com.hashmap.models;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AuctionTest {

    @Test
    public void testCreateAuction() {
        Auction auction = new Auction(new Item("jet engines","mach 3 speed"),new BigDecimal(500));
        Assert.assertNotNull(auction.getId());
        Assert.assertNotNull(auction.getStartingAuctionPrice());
        Assert.assertNotNull(auction.getItem().getName());
        Assert.assertNotNull(auction.getItem().getDescription());
    }

}
