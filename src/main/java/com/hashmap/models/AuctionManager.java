package com.hashmap.models;


import java.util.List;
import java.util.UUID;

public class AuctionManager {

    public void createAuction(Auction auction) {
        DataBase.addAuctionInList(auction);
    }

    public List<Auction> listOfOpenAuction() {
        return  DataBase.getListOfAuction();
    }

    public Auction getAuction(UUID auctionId)
    {
        try
        {
            DataBase.getAuction(auctionId);
        }
        catch (Exception exception)
        {
            System.out.println(exception);
        }
        return  null;
    }

    public void placeBid(UUID auctionId,Bid bid)
    {

        try
        {
            if(DataBase.getAuction(auctionId).getIsAuctionOpen())
                DataBase.updateCurrentBid(auctionId, bid);
            else
                System.out.println("Auction is closed now ");
        }
        catch (Exception exception)
        {
            System.out.println(exception);
        }
    }
}
