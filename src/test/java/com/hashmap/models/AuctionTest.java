package com.hashmap.models;

import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;
import com.hashmap.models.auction.Item;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class AuctionTest {

    @Test
    public void testAuction() {
        Auction auction;
        auction = new Auction(new Item("jet engines", "mach 3 speed"),
                              UUID.randomUUID(), new BigDecimal(300), 3);

        Assert.assertNotNull(auction.getAuctionId());
        Assert.assertNotNull(auction.getSellerId());
        Assert.assertNotNull(auction.getOpeningAuctionPrice());
        Assert.assertNotNull(auction.getItem().getName());
        Assert.assertNotNull(auction.getItem().getDescription());
        }
}
