package com.hashmap.service;

import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;
import com.hashmap.models.auction.Item;
import com.hashmap.models.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;


import static java.lang.Thread.sleep;

public class AuctionServiceTest {


    User seller;
    User buyer;
    UserService userService;

    AuctionService auctionService;
    Auction auction;

    @Before
    public void beforeEachTestCase()
    {
        seller = new User("abc", "abc@gmail.com");
        buyer  = new User("xyz","xyz@gmail.com");

        userService = new UserService();

        userService.createUser(seller);
        userService.createUser(buyer);

        auctionService = new AuctionService();

        auction = new Auction(new Item("jet engines", "mach 3 "), seller.getUserId(), new BigDecimal(100), 5);

    }

    @Test
    public void testAddAuctionSuccess()
    {
        Assert.assertEquals(auctionService.addAuction(auction),"Auction is added");

    }

    @Test
    public void testAddAuctionFailure()
    {
        Assert.assertEquals(auctionService.addAuction(null),"Auction is not added");
    }


    @Test
    public void testGetAuctionSuccess()
    {
        auctionService.addAuction(auction);
        Assert.assertEquals(auctionService.getAuction(auction.getAuctionId()),auction);
    }


    @Test
    public void testGetAuctionFailure()
    {
        Assert.assertNull(auctionService.getAuction(UUID.randomUUID()));
    }


    @Test
    public  void testPlaceBidShouldReturnSuccess()
    {

        auctionService.addAuction(auction);
        //place bid
        String message = auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(600)));
        Assert.assertEquals(message,"Bid placed");

    }


    @Test
    public  void testPlaceBidShouldReturnAuctionIsClosed()throws Exception
    {

        auctionService.addAuction(auction);

        long extraTime  = 1000;
        sleep(auction.getEndTimeInSeconds()*1000 + extraTime);

        //place bid after auction get closed
        String message = auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(800)));

        Assert.assertEquals(message,"Auction is closed now ");
    }

    @Test
    public  void testPlaceBidShouldReturnAuctionNotPresent()
    {

       // auctionService.addAuction(auction);
        String message = auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(600)));
        Assert.assertEquals(message,"Trying to Bid on the Auction which is not present");

    }

    @Test
    public  void testPlaceBidShouldReturnBidIsLower()
    {

        auctionService.addAuction(auction);

        //place 1st bid
        auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(600)));
        //place 2nd  bid lower
        String message = auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(300)));

        Assert.assertEquals(message,"Bid price is lower than the current bid");

    }

}
