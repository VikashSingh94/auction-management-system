package com.hashmap.service;

import com.hashmap.core.Auction.AuctionStatus;
import com.hashmap.core.Auction.BidStatus;
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
import java.util.UUID;


import static java.lang.Thread.sleep;

public class AuctionServiceTest {


    User seller;
    User buyer;
    UserService userService;

    PaymentGateWay paymentGateWay;
    AuctionService auctionService;
    Auction auction;

    @Before
    public void beforeEachTestCase() {
        seller = new User("abc", "abc@gmail.com");
        buyer = new User("xyz", "xyz@gmail.com");

        userService = new UserService();

        userService.createUser(seller);
        userService.createUser(buyer);

        auctionService = new AuctionService();
        paymentGateWay = new PaymentGateWayImpl();

        Item item = new Item("jet engines", "mach 3 ");

        auction = new Auction(item, seller.getUserId(), new BigDecimal(100), 5);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    @Test
    public void testAddAuctionSuccess() {
        Assert.assertEquals(auctionService.addAuction(auction), AuctionStatus.AUCTION_ADDED);
    }

    @Test
    public void testAddAuctionFailure() {
        Assert.assertEquals(auctionService.addAuction(null), AuctionStatus.AUCTION_NOT_ADDED);
    }


    @Test
    public void testGetAuctionSuccess()  {
        auctionService.addAuction(auction);
        Assert.assertEquals(auctionService.getAuction(auction.getAuctionId()), auction);
    }


    @Test
    public void testGetAuctionFailure()  {
        expectedEx.expect(InvalidAuction.class);
        expectedEx.expectMessage("Auction is not present");

        UUID randomAuctionId = UUID.randomUUID();
        Assert.assertNull(auctionService.getAuction(randomAuctionId));
    }


    @Test
    public void testPlaceBidShouldReturnSuccess() {
        auctionService.addAuction(auction);
        paymentGateWay.add(buyer.getUserId(), new BigDecimal(1000));

        Bid bid = new Bid(buyer.getUserId(), new BigDecimal(600));
        UUID auctionId = auction.getAuctionId();

        Assert.assertEquals(auctionService.placeBid(auctionId,bid), BidStatus.BID_ADDED);
    }


    @Test
    public void testPlaceBidShouldReturnAuctionIsClosed() throws Exception {
        expectedEx.expect(InvalidAuction.class);
        expectedEx.expectMessage("Auction is closed now ");

        paymentGateWay.add(buyer.getUserId(), new BigDecimal(1000));
        auctionService.addAuction(auction);

        long extraTime = 1000;
        sleep(auction.getEndTimeInSeconds() * 1000 + extraTime);

        Bid bid = new Bid(buyer.getUserId(), new BigDecimal(800));

        //place bid after auction get closed
        auctionService.placeBid(auction.getAuctionId(),bid);
    }


    @Test
    public void testPlaceBidShouldReturnAuctionNotPresent()  {
        expectedEx.expect(InvalidAuction.class);
        expectedEx.expectMessage("Auction is not present");

        UUID randomAuctionId = UUID.randomUUID();
        Bid bid = new Bid(buyer.getUserId(), new BigDecimal(600));

        auctionService.placeBid(randomAuctionId, bid);
    }

    @Test
    public void testPlaceBidShouldReturnBidIsLower()  {
        expectedEx.expect(InvalidBid.class);
        expectedEx.expectMessage("Bid price is lower than the current bid");


        paymentGateWay.add(buyer.getUserId(), new BigDecimal(1000));
        auctionService.addAuction(auction);

        //place 1st bid
        Bid bid = new Bid(buyer.getUserId(), new BigDecimal(600));
        auctionService.placeBid(auction.getAuctionId(), bid);

        //place 2nd  bid lower
        bid = new Bid(buyer.getUserId(), new BigDecimal(300));
        auctionService.placeBid(auction.getAuctionId(), bid);

    }

    @Test
    public void testPlaceBidShouldReturnInSufficientBalance() {
        expectedEx.expect(InSufficientBalance.class);
        expectedEx.expectMessage("Not SufficientBalance");

        auctionService.addAuction(auction);

        Bid bid = new Bid(buyer.getUserId(), new BigDecimal(600));

        auctionService.placeBid(auction.getAuctionId(),bid);

    }

}
