package com.hashmap.service;


import com.hashmap.models.Auction;
import com.hashmap.models.Item;
import com.hashmap.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;



@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class AuctionServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private AuctionService auctionService;

    private User seller;
    private User buyer;
    private Auction auction;

    @Before
    public void beforeEachTestCase() {
        seller = new User("abc", "abc@gmail.com");
        buyer = new User("xyz", "xyz@gmail.com");

        seller = userService.addUser(seller);
        buyer = userService.addUser(buyer);

        Item item = new Item("jet engines", "mach 3 ");

        auction = new Auction(item, seller.getUserId(), new BigDecimal(100), 5);
    }



    @Test
    public void testAddAuction() {
        Assert.assertNotNull(auctionService.addAuction(auction));
    }


    @Test(expected = NullPointerException.class)
    public void testAddNullAuction() {
        auctionService.addAuction(null);
    }


    @Test
    public void testGetAuctionByKnownId()  {
        auction = auctionService.addAuction(auction);
        Assert.assertNotNull(auctionService.getAuction(auction.getAuctionId()).get());
    }


    @Test
    public void testGetAuctionByUnknownId()  {
        UUID randomAuctionId = UUID.randomUUID();
        Assert.assertEquals(auctionService.getAuction(randomAuctionId), Optional.empty());
    }

    @Test
    public void testStartAuctionTimer(){
        auction = auctionService.addAuction(auction);

        auctionService.startAuctionTimer(auction.getAuctionId());

        auction = auctionService.getAuction(auction.getAuctionId()).get();

        Assert.assertTrue(auction.isAuctionOpen());
    }

}
