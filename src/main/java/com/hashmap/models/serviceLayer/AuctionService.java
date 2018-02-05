package com.hashmap.models.serviceLayer;


import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;
import com.hashmap.models.dataAccessLayer.DataAccessLayer;
import com.hashmap.models.dataAccessLayer.InMemoryDAO;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import static java.util.stream.Collectors.toList;


public class AuctionService
{

    DataAccessLayer dataAccessLayer;
    Map<UUID,CountDownTimerService> timers = new TreeMap<>();


    public AuctionService(){
      dataAccessLayer = new InMemoryDAO();
    }


    public String addAuction(Auction auction) {

        if(auction!= null) {
            if (dataAccessLayer.addAuction(auction)) {
                timers.put(auction.getAuctionId(), new CountDownTimerService(auction.getEndTimeInSeconds(), auction.getAuctionId()));
                return "Auction is added";
            }
        }
        return "Auction is not added";
    }

    public List<Auction> runningAuctions() {

       List<Auction> auctions = dataAccessLayer.getAuctions();
       return auctions.stream().filter(auction ->(auction.getIsAuctionOpen())).collect(toList());
    }

    public Auction getAuction(UUID auctionId) {
           return dataAccessLayer.getAuction(auctionId);
    }

    public String placeBid(UUID auctionId,Bid bid)
    {
        Auction auction = dataAccessLayer.getAuction(auctionId);

        if( auction!= null) {
            if (auction.getIsAuctionOpen())
                return updateCurrentBid(auction,bid);
            else
                return "Auction is closed now ";
        }
        else {
            return "Trying to Bid on the Auction which is not present";
        }
    }


    public String updateCurrentBid(Auction auction, Bid bid)
    {
        if(auction.getCurrentBid() == null || (auction.getCurrentBid().getBidPrice().compareTo(bid.getBidPrice()) < 0))
        {
            auction.setCurrentBid(bid);
            dataAccessLayer.addAuction(auction);
            return "Bid placed";
        }
        return "Bid price is lower than the current bid";

    }


}
