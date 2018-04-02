package com.hashmap.service;

import com.hashmap.exception.InsufficientFundsException;
import com.hashmap.exception.InvalidAuctionException;
import com.hashmap.exception.InvalidBidException;
import com.hashmap.models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static java.lang.Thread.sleep;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class BidServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private BidService bidService;
    @Autowired
    private WalletService walletService;

    private User seller;
    private User buyer;
    private Auction auction;

    @Before
    public void setUp(){

        seller = userService.addUser(new User("abc", "abc@gmail.com"));
        buyer =  userService.addUser(new User("xyz", "xyz@gmail.com"));


        Item item = new Item("jet engines", "mach 3 ");
        auction = new Auction(item, seller.getUserId(), new BigDecimal(100), 5);

        auction = auctionService.addAuction(auction);

        auctionService.startAuctionTimer(auction.getAuctionId());

        walletService.credit(buyer.getUserId(), new BigDecimal(1000));

    }


    @Test
    public void testPlaceBidShouldReturnSuccess() {

        Bid bid = new Bid(buyer.getUserId(), new BigDecimal(600));

        UUID auctionId = auction.getAuctionId();

        Assert.assertNotNull(bidService.placeBid(auctionId, bid));
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testPlaceBidShouldReturnAuctionIsClosed() throws Exception {
        expectedEx.expect(InvalidAuctionException.class);
        expectedEx.expectMessage("Auction is closed now ");

        long extraTime = 1000;
        sleep(auction.getEndTime() * 1000 + extraTime);

        Bid bid = new Bid(buyer.getUserId(), new BigDecimal(200));

        //place bid after auction get closed
        bidService.placeBid(auction.getAuctionId(),bid);
    }


    @Test
    public void testPlaceBidShouldReturnBidIsLower()  {
        expectedEx.expect(InvalidBidException.class);
        expectedEx.expectMessage("Bid price is lower than the current bid");


        //place 1st bid
        Bid bid = new Bid(buyer.getUserId(), new BigDecimal(600));
        bidService.placeBid(auction.getAuctionId(), bid);

        //place 2nd  bid lower
        bid = new Bid(buyer.getUserId(), new BigDecimal(300));
        bidService.placeBid(auction.getAuctionId(), bid);

    }


    @Test
    public void testPlaceBidShouldReturnAuctionNotPresent()  {
        expectedEx.expect(InvalidAuctionException.class);
        expectedEx.expectMessage("Auction is not present");

        UUID randomAuctionId = UUID.randomUUID();
        Bid bid = new Bid(buyer.getUserId(), new BigDecimal(600));

        bidService.placeBid(randomAuctionId, bid);
    }


    @Test
    public void testPlaceBidShouldReturnInSufficientBalance() {
        expectedEx.expect(InsufficientFundsException.class);
        expectedEx.expectMessage("not sufficient balance");

        Bid bid = new Bid(buyer.getUserId(), new BigDecimal(2000));

        bidService.placeBid(auction.getAuctionId(),bid);

    }

}
