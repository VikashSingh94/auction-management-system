package com.hashmap.models;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.lang.Thread.sleep;

public class AuctionSystemTest {

    @Test
    public  void testAuctionBeforeClosing()throws Exception
    {
        User seller = new User("abc","abc@gmail.com");
        User buyer  = new User("xyz","xyz@gmail.com");

        AuctionManager auctionManager = new AuctionManager();

        Auction auction;
        auction = new Auction(new Item("jet engines", "mach 3 "), new Bid(seller.getUserId(),new BigDecimal(100)), 5);

        auctionManager.createAuction(auction);


        List<Auction> auctions = DataBase.getListOfAuction();

        auction = auctions.get(0);

        //check auction is open
        Assert.assertEquals(auction.getIsAuctionOpen(),true);

        //place bid
        auctionManager.placeBid(auction.getAuctionId(),new Bid(seller.getUserId(),new BigDecimal(600)));


        Assert.assertEquals(DataBase.getAuction(auction.getAuctionId()).getCurrentBid().getBidPrice(),new BigDecimal(600));

    }

    @Test
    public  void testAuctionAfterClosing()throws Exception
    {
        User seller = new User("abc","abc@gmail.com");
        User buyer  = new User("xyz","xyz@gmail.com");

        AuctionManager auctionManager = new AuctionManager();

        Auction auction;
        auction = new Auction(new Item("jet engines", "mach 3 "), new Bid(seller.getUserId(),new BigDecimal(100)), 5);

        auctionManager.createAuction(auction);


        List<Auction> auctions = DataBase.getListOfAuction();

        auction = auctions.get(0);

        //check auction is open
        Assert.assertEquals(auction.getIsAuctionOpen(),true);

        //place bid
        auctionManager.placeBid(auction.getAuctionId(),new Bid(seller.getUserId(),new BigDecimal(600)));


        Assert.assertEquals(DataBase.getAuction(auction.getAuctionId()).getCurrentBid().getBidPrice(),new BigDecimal(600));

        sleep(6000);

        //place bid after auction get closed
        auctionManager.placeBid(auction.getAuctionId(),new Bid(seller.getUserId(),new BigDecimal(800)));

        Assert.assertEquals(DataBase.getAuction(auction.getAuctionId()).getCurrentBid().getBidPrice(),new BigDecimal(600));


    }
}
