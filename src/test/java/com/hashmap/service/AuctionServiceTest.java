package com.hashmap.service;

import com.hashmap.exception.InSufficientBalance;
import com.hashmap.exception.InvalidAuction;
import com.hashmap.exception.InvalidBid;
import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;
import com.hashmap.models.auction.Item;
import com.hashmap.models.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.UUID;


import static com.sun.applet2.preloader.event.ConfigEvent.STATUS;
import static java.lang.Thread.sleep;

public class AuctionServiceTest {


    User seller;
    User buyer;
    UserService userService;

    PaymentGateWay paymentGateWay;
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
        paymentGateWay = new PaymentGateWayImpl();

        auction = new Auction(new Item("jet engines", "mach 3 "), seller.getUserId(), new BigDecimal(100), 5);

    }

    @Test
    public void testAddAuctionSuccess()
    {
        Assert.assertEquals(auctionService.addAuction(auction),Status.AUCTION_ADDED);

    }

    @Test
    public void testAddAuctionFailure()
    {
        Assert.assertEquals(auctionService.addAuction(null),Status.AUCTION_NOT_ADDED);
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
    public  void testPlaceBidShouldReturnSuccess()throws Exception
    {
        auctionService.addAuction(auction);
        paymentGateWay.add(buyer.getUserId(),new BigDecimal(1000));

        Assert.assertEquals(auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(600))),
                            Status.BID_ADDED);
    }


    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public  void testPlaceBidShouldReturnAuctionIsClosed()throws  Exception
    {
        expectedEx.expect(InvalidAuction.class);
        expectedEx.expectMessage("Auction is closed now ");

        paymentGateWay.add(buyer.getUserId(),new BigDecimal(1000));
        auctionService.addAuction(auction);

        long extraTime  = 1000;
        sleep(auction.getEndTimeInSeconds()*1000 + extraTime);

        //place bid after auction get closed
        auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(800)));
    }


    @Test
    public  void testPlaceBidShouldReturnAuctionNotPresent()throws Exception
    {
        expectedEx.expect(InvalidAuction.class);
        expectedEx.expectMessage("Trying to Bid on the Auction which is not present");

        auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(600)));
    }

    @Test
    public  void testPlaceBidShouldReturnBidIsLower()throws Exception
    {
        expectedEx.expect(InvalidBid.class);
        expectedEx.expectMessage("Bid price is lower than the current bid");


        paymentGateWay.add(buyer.getUserId(),new BigDecimal(1000));
        auctionService.addAuction(auction);

        //place 1st bid
        auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(600)));
        //place 2nd  bid lower
        auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(300)));

    }

    @Test
    public  void testPlaceBidShouldReturnInSufficientBalance()throws Exception
    {
        expectedEx.expect(InSufficientBalance.class);
        expectedEx.expectMessage("Not SufficientBalance");

        auctionService.addAuction(auction);

        auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(600)));

    }
}
