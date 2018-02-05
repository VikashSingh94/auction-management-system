package com.hashmap.models;

import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;
import com.hashmap.models.auction.Item;
import com.hashmap.models.serviceLayer.AuctionService;
import com.hashmap.models.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;


import static java.lang.Thread.sleep;

public class AuctionServiceTest {


    User seller;
    User buyer;

    AuctionService auctionService;
    Auction auction;

    @Before
    public void beforeEachTestCase()
    {
        seller = new User("abc", "abc@gmail.com");
        buyer  = new User("xyz","xyz@gmail.com");

        auctionService = new AuctionService();

        auction = new Auction(new Item("jet engines", "mach 3 "), new Bid(seller.getUserId(), new BigDecimal(100)), 5);

    }

    @Test
    public void addAuctionSuccess()
    {
        Assert.assertEquals(auctionService.addAuction(auction),"Auction is added");
    }


    @Test
    public void getAuctionSuccess()
    {
        auctionService.addAuction(auction);
        Assert.assertEquals(auctionService.getAuction(auction.getAuctionId()),auction);
    }


    @Test
    public void getAuctionFailure()
    {
        Assert.assertNull(auctionService.getAuction(UUID.randomUUID()));
    }



    @Test
    public  void placeBidAuctionIsOpen()
    {

        auctionService.addAuction(auction);


        List<Auction> auctions = auctionService.runningAuctions();

        auction = auctions.get(0);

        //check auction is open
        Assert.assertEquals(auction.getIsAuctionOpen(),true);

        //place bid
        String message = auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(600)));


        Assert.assertEquals(message,"Bid placed");

    }


    @Test
    public  void BidIsNotPlaced_AfterClosing()throws Exception
    {

        auctionService.addAuction(auction);

        List<Auction> openAuctions = auctionService.runningAuctions();

        Random rand = new Random();
        int randomIndex = rand.nextInt(openAuctions.size());

        Auction randomRunningAuction = openAuctions.get(randomIndex);


        long extraTime  = 1000;
        sleep(randomRunningAuction.getEndTimeInSeconds()*1000 + extraTime);



        //place bid after auction get closed
        auctionService.placeBid(randomRunningAuction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(800)));

        Assert.assertEquals(auctionService.getAuction(randomRunningAuction.getAuctionId()).getCurrentBid(), null);
    }



    @Test
    public  void placeBidCurrentBidIsLower()
    {

        auctionService.addAuction(auction);


        List<Auction> auctions = auctionService.runningAuctions();

        auction = auctions.get(0);

        //check auction is open
        Assert.assertEquals(auction.getIsAuctionOpen(),true);

        //place 1st bid
        auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(600)));
        //place 2nd  bid lower
        String message = auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(300)));


        Assert.assertEquals(message,"Bid price is lower than the current bid");

    }



    @Test
    public  void placeBidAuctionNotPresent()
    {

       // auctionService.addAuction(auction);
        String message = auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(600)));
        Assert.assertEquals(message,"Trying to Bid on the Auction which is not present");

    }




}


//@Test
//    public  void testPlaceBidWhenAuctionIsOpenIsAddedInDBMS()
//    {
//
//        auctionService.addAuction(auction);
//
//
//        List<Auction> auctions = auctionService.runningAuctions();
//
//        auction = auctions.get(0);
//
//        //check auction is open
//        Assert.assertEquals(auction.getIsAuctionOpen(),true);
//
//        //place bid
//        auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(600)));
//
//
//        Assert.assertNotNull(auctionService.getAuction(auction.getAuctionId()).getCurrentBid());
//
//    }