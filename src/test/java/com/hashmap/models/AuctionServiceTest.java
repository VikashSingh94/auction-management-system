package com.hashmap.models;

import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;
import com.hashmap.models.auction.Item;
import com.hashmap.models.serviceLayer.AuctionService;
import com.hashmap.models.user.User;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class AuctionServiceTest {

    @Test

    public  void PlaceBid_OnAuctionIsOpen()throws Exception
    {
        User seller = new User("abc", "abc@gmail.com");
        User buyer  = new User("xyz","xyz@gmail.com");


        Auction auction;
        auction = new Auction(new Item("jet engines", "mach 3 "), new Bid(seller.getUserId(), new BigDecimal(100)), 5);

        AuctionService auctionService = new AuctionService();
        auctionService.createAuction(auction);


        List<Auction> auctions = auctionService.runningAuctions();

        auction = auctions.get(0);

        //check auction is open
        Assert.assertEquals(auction.getIsAuctionOpen(),true);

        //place bid
        auctionService.placeBid(auction.getAuctionId(),new Bid(buyer.getUserId(),new BigDecimal(600)));


        Assert.assertNotNull(auctionService.getAuction(auction.getAuctionId()).getCurrentBid());

    }


    @Test
    public  void BidIsNotPlaced_AfterClosing()throws Exception
    {
        User seller = new User("abc","abc@gmail.com");
        User buyer  = new User("xyz","xyz@gmail.com");


        Auction auction;
        auction = new Auction(new Item("jet engines", "mach 3 "),
                              new Bid(seller.getUserId(),new BigDecimal(100)), 5);

        AuctionService auctionService = new AuctionService();
        auctionService.createAuction(auction);


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

}
