package com.hashmap.service;

import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;
import com.hashmap.models.auction.Item;
import com.hashmap.models.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static java.lang.Thread.sleep;

public class AuctionSystemTest {

    User seller;
    User buyerOne;
    User buyerTwo;
    UserService userService;

    AuctionService auctionService;
    Auction auctionOne;
    Auction auctionTwo;

    PaymentGateWay paymentGateWay;


    @Before
    public void beforeEachTestCase()
    {
        seller = new User("abc", "abc@gmail.com");
        buyerOne  = new User("xyz","xyz@gmail.com");
        buyerTwo = new User("vik","vik@gmail.com");

        userService = new UserService();
        paymentGateWay = new PaymentGateWayImpl();
        auctionService = new AuctionService();

        auctionOne = new Auction(new Item("jet engines", "mach 3 "), seller.getUserId(), new BigDecimal(100), 5);
        auctionTwo = new Auction(new Item("Plane engines", "mach 9 "), seller.getUserId(), new BigDecimal(100), 5);

    }
/*
    @Test
    public void testPaymentAfterAuctionClosed()throws Exception
    {
        userService.createUser(seller);
        userService.createUser(buyerOne);
        userService.createUser(buyerTwo);

        paymentGateWay.add(buyerOne.getUserId(),new BigDecimal(1000));
        paymentGateWay.add(buyerTwo.getUserId(),new BigDecimal(800));


        auctionService.addAuction(auctionOne);
        auctionService.addAuction(auctionTwo);

        auctionService.placeBid(auctionOne.getAuctionId(),new Bid(buyerOne.getUserId(),new BigDecimal(200)));
        auctionService.placeBid(auctionOne.getAuctionId(),new Bid(buyerTwo.getUserId(),new BigDecimal(300)));


        auctionService.placeBid(auctionTwo.getAuctionId(),new Bid(buyerTwo.getUserId(),new BigDecimal(200)));
        auctionService.placeBid(auctionTwo.getAuctionId(),new Bid(buyerOne.getUserId(),new BigDecimal(300)));


        sleep(6000);


        Assert.assertEquals(seller.getWallet().getTotalBalanceInWallet(),new BigDecimal(600));
        Assert.assertEquals(buyerOne.getWallet().getTotalBalanceInWallet(),new BigDecimal(700));
        Assert.assertEquals(buyerTwo.getWallet().getTotalBalanceInWallet(),new BigDecimal(500));

    }
    */

    @Test
    public void testPaymentAfterAuctionClosed()throws Exception
    {
        userService.createUser(seller);
        userService.createUser(buyerOne);
        userService.createUser(buyerTwo);

        paymentGateWay.add(buyerOne.getUserId(),new BigDecimal(1000));
        paymentGateWay.add(buyerTwo.getUserId(),new BigDecimal(800));


        auctionService.addAuction(auctionOne);

        auctionService.placeBid(auctionOne.getAuctionId(),new Bid(buyerOne.getUserId(),new BigDecimal(200)));
        auctionService.placeBid(auctionOne.getAuctionId(),new Bid(buyerTwo.getUserId(),new BigDecimal(300)));




        sleep(6000);


        Assert.assertEquals(seller.getWallet().getTotalBalanceInWallet(),new BigDecimal(300));
        Assert.assertEquals(buyerOne.getWallet().getTotalBalanceInWallet(),new BigDecimal(1000));
        Assert.assertEquals(buyerTwo.getWallet().getTotalBalanceInWallet(),new BigDecimal(500));

    }
}
