package com.hashmap.service;

import com.hashmap.exception.InSufficientBalance;
import com.hashmap.exception.InvalidAuction;
import com.hashmap.exception.InvalidBid;
import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;
import com.hashmap.dao.InMemoryDoa;
import com.hashmap.dao.InMemoryDAOImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import static java.util.stream.Collectors.toList;


public class AuctionService
{

    InMemoryDoa dataAccessLayer;
    PaymentGateWay paymentGateWay;
    Map<UUID,TimerService> timers = new TreeMap<>();


    //Reduce the dependency,Initialize in constructor,
    //that member function can access it.
    public AuctionService(){
      dataAccessLayer = new InMemoryDAOImpl();
      paymentGateWay = new PaymentGateWayImpl();
    }


    //Function should be doing one thing
    //So,we created function StartTimeWithListenerCallBack
    public Status addAuction(Auction auction) {

        if(auction!= null) {
            if (dataAccessLayer.addAuction(auction))
            {
                startAuctionTimerWithListenerCallBack(auction);
                return Status.AUCTION_ADDED;
            }
        }
        return Status.AUCTION_NOT_ADDED;
    }

    public  void startAuctionTimerWithListenerCallBack(Auction auction)
    {
        Listener listener = new AuctionListener(auction.getAuctionId());
        timers.put(auction.getAuctionId(), new TimerService(auction.getEndTimeInSeconds(), listener));
    }



    public List<Auction> runningAuctions() {

       List<Auction> auctions = dataAccessLayer.getAuctions();
       return auctions.stream().filter(auction ->(auction.getIsAuctionOpen())).collect(toList());
    }

    public Auction getAuction(UUID auctionId) {
           return dataAccessLayer.getAuction(auctionId);
    }


    public Status placeBid(UUID auctionId,Bid bid)throws Exception
    {
        Auction auction = dataAccessLayer.getAuction(auctionId);

        if( auction!= null) {


            UUID userId = bid.getUserId();
            BigDecimal bidPrice = bid.getBidPrice();

            if(paymentGateWay.checkSufficientBalance(userId,bidPrice).equals(Status.NOT_SUFFICIENTBALANCE))
                throw new InSufficientBalance("Not SufficientBalance");

            if (auction.getIsAuctionOpen())
                return updateCurrentBid(auction,bid);
            else
                throw new InvalidAuction("Auction is closed now ");
        }
        else {
            throw new InvalidAuction("Trying to Bid on the Auction which is not present");
        }
    }



    public Status updateCurrentBid(Auction auction, Bid bid)throws InvalidBid
    {
        if(auction.getCurrentBid() == null)
        {
            if(auction.getOpeningAuctionPrice().compareTo(bid.getBidPrice()) < 0) {
                dataAccessLayer.updateCurrentBid(auction.getAuctionId(),bid);
                return Status.BID_ADDED;
            }

        }
        else if( (auction.getCurrentBid().getBidPrice().compareTo(bid.getBidPrice()) < 0))
        {
            dataAccessLayer.updateCurrentBid(auction.getAuctionId(),bid);
            return Status.BID_ADDED;
        }

        throw new InvalidBid("Bid price is lower than the current bid");

    }

}
