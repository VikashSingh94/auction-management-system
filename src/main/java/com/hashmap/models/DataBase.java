package com.hashmap.models;

import java.util.*;

public   class DataBase {

    private static List<Auction> auctions = Collections.synchronizedList(new ArrayList<Auction>());
    private static List<User> users = new ArrayList<User>();

    public static List getListOfAuction() {
        return auctions;
    }

    public static void addAuctionInList(Auction auction) {
        auctions.add(auction);
    }

    public static void updateIsAuctionOpen(UUID auctionId, boolean isAuctionOpen) {

        for (Auction auction : auctions) {
            if (auction.getAuctionId().equals(auctionId)) {
                auction.setAuctionOpen(isAuctionOpen);
                return;
            }
        }

        throw new IllegalStateException("auction not present in Database");

    }

    public static void updateCurrentBid(UUID auctionId, Bid bid)throws Exception {
       for(Auction auction: auctions)
       {
           if(auction.getAuctionId().equals(auctionId))
           {
               if(auction.getCurrentBid() == null)
               {
                   auction.setCurrentBid(bid);
                   return;
               }
               else if(auction.getCurrentBid().getBidPrice().compareTo(bid.getBidPrice()) < 0)
               {
                   auction.setCurrentBid(bid);
                   return;
               }
               else
               {
                   throw new IllegalArgumentException("Bid price is lower than the current bid");
               }
           }
       }

       throw new IllegalAccessException("No such Auction exist");
    }

    public static Auction getAuction(UUID auctionId)throws Exception {
        for (Auction auction : auctions) {
            if(auction.getAuctionId().equals(auctionId))
            {
                return  auction;
            }
        }
        throw  new IllegalAccessException("No such Auction present");
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static User getUser(UUID userId)throws Exception {
        for (User user : users) {
            if(user.getUserId().equals(userId))
            {
                return  user;
            }
        }
        throw  new IllegalAccessException("No such User present");
    }
}