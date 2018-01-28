package com.hashmap.models;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.lang.Thread.sleep;

public class AuctionManagerTest {

    @Test
    public void testCreateAuctionManager()throws Exception
    {
        //TODO: Refactor the test into three different test cases
        //Creation of auction
        //Auction is open before the ending time
        //Auction is closed after ending time
        AuctionManager auctionManager = new AuctionManager();

        Auction auction;
        auction = new Auction(new Item("jet engines", "mach 3 "), new Bid(UUID.randomUUID(),new BigDecimal(1000)), 5);

        auctionManager.createAuction(auction);

        List<Auction> list;
        list = DataBase.getListOfAuction();

        Assert.assertEquals(list.get(0).getIsAuctionOpen(),true);

        sleep(6000);

        list = DataBase.getListOfAuction();

        Assert.assertEquals(list.get(0).getIsAuctionOpen(),false);

    }
}

//TODO: Test case for the multiple auction creation simultaneously

//        AuctionManager auctionManager = new AuctionManager();
//
//        Auction auction1 = new Auction(new Item("jet engines", "mach 3 "), new BigDecimal(1000), 3);
//        Auction auction2 = new Auction(new Item("bomber engines", "mach 2 "), new BigDecimal(100), 4);
//        Auction auction3 = new Auction(new Item("plane engines", "mach 3.5 "), new BigDecimal(10), 8);
//
//        auctionManager.createAuction(auction1);
//        auctionManager.createAuction(auction2);
//        auctionManager.createAuction(auction3);
//
//
//        sleep(5000);
//
//        List<Auction> list = DataBase.getListOfAuction();
//        for (Auction auction : list) {
//            System.out.println(auction.getItem().getName() + " " + auction.getIsAuctionOpen());
//        }
