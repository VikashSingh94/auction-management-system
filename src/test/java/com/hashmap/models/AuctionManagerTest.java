package com.hashmap.models;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.lang.Thread.sleep;

public class AuctionManagerTest {

    @Test
    public void createAuction_IsOpen_TillEndTime()throws Exception
    {
        AuctionManager auctionManager = new AuctionManager();

        Auction auction;
        auction = new Auction(new Item("jet engines", "mach 3 "), new Bid(UUID.randomUUID(),new BigDecimal(1000)), 5);

        auctionManager.createAuction(auction);

        List<Auction> auctions;
        auctions = DataBase.getListOfAuction();

        Assert.assertEquals(auctions.get(0).getIsAuctionOpen(),true);

    }

    @Test
    public  void createAuction_IsClosed_AfterTimeEnd()throws Exception
    {
        AuctionManager auctionManager = new AuctionManager();

        Auction auction;
        auction = new Auction(new Item("jet engines", "mach 3 "), new Bid(UUID.randomUUID(),new BigDecimal(1000)), 5);

        auctionManager.createAuction(auction);

        sleep(6000);

        List<Auction> auctions;
        auctions = DataBase.getListOfAuction();

        Assert.assertEquals(auctions.get(0).getIsAuctionOpen(),false);
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
