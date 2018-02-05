//package com.hashmap.models.storage;
//
//import com.hashmap.models.auction.Auction;
//import com.hashmap.models.auction.Bid;
//import com.hashmap.models.user.User;
//
//import java.util.*;
//
//public   class InMemoryDataBase  {
//
//    // static variable single_instance of type InMemoryDataBase
//    private static InMemoryDataBase single_instance = null;
//
//    private  List<Auction> auctions = Collections.synchronizedList(new ArrayList<Auction>());
//    private  List<User> users = new ArrayList<User>();
//
//
//    // private constructor restricted to this class itself
//    private InMemoryDataBase()
//    {
//
//    }
//
//    // static method to create instance of InMemoryDataBase class
//    public static InMemoryDataBase getInstance()
//    {
//        if (single_instance == null)
//            single_instance = new InMemoryDataBase();
//
//        return single_instance;
//    }
//
//
//    public  List getAuctions() {
//        return auctions;
//    }
//
//    public  boolean addAuction(Auction auction) {
//        auctions.add(auction);
//        return true;
//    }
//
//    public  boolean updateIsAuctionOpen(UUID auctionId, boolean isAuctionOpen) {
//
//        for (Auction auction : auctions) {
//            if (auction.getAuctionId().equals(auctionId)) {
//                auction.setAuctionOpen(isAuctionOpen);
//                return true;
//            }
//        }
//
//        throw new IllegalStateException("auction not present in Database");
//
//        }
//
//    public  boolean updateCurrentBid(UUID auctionId, Bid bid) {
//       for(Auction auction: auctions)
//       {
//           if(auction.getAuctionId().equals(auctionId))
//           {
//               if(auction.getCurrentBid() == null)
//               {
//                   auction.setCurrentBid(bid);
//                   return;
//               }
//               else if(auction.getCurrentBid().getBidPrice().compareTo(bid.getBidPrice()) < 0)
//               {
//                   auction.setCurrentBid(bid);
//                   return;
//               }
//               else
//               {
//                   throw new IllegalArgumentException("Bid price is lower than the current bid");
//               }
//           }
//       }
//
//       throw new IllegalArgumentException("No such Auction exist");
//    }
//
//    public  Auction getAuction(UUID auctionId) {
//        for (Auction auction : auctions) {
//            if(auction.getAuctionId().equals(auctionId))
//            {
//                return  auction;
//            }
//        }
//        throw  new IllegalArgumentException("No such Auction present");
//    }
//
//    public  boolean addUser(User user) {
//        users.add(user);
//    }
//
//    public  User getUser(UUID userId) {
//        for (User user : users) {
//            if(user.getUserId().equals(userId))
//            {
//                return  user;
//            }
//        }
//        throw  new IllegalArgumentException("No such User present");
//    }
//
//}

package com.hashmap.models.storage;

import com.hashmap.models.auction.Auction;
import com.hashmap.models.user.User;

import java.util.*;

public   class InMemoryDataBase  {

    // static variable single_instance of type InMemoryDataBase
    private static InMemoryDataBase single_instance = null;

    private  List<Auction> auctions = Collections.synchronizedList(new ArrayList<Auction>());
    private  List<User> users = new LinkedList<>();


    // private constructor restricted to this class itself
    private InMemoryDataBase()
    {

    }

    // static method to create instance of InMemoryDataBase class
    public static InMemoryDataBase getInstance()
    {
        if (single_instance == null)
            single_instance = new InMemoryDataBase();

        return single_instance;
    }




    public  List getAuctions() {
        return auctions;
    }



    public  boolean addAuction(Auction auction) {
        auctions.add(auction);
        return true;
    }

    public  Auction getAuction(UUID auctionId) {
        for (Auction auction : auctions) {
            if(auction.getAuctionId().equals(auctionId))
            {
                return  auction;
            }
        }

        return null;
    }



    public boolean updateAuction(Auction auction)
    {
        for (Auction tempAuction : auctions) {
            if(tempAuction.getAuctionId().equals(auction.getAuctionId()))
            {
                auctions.remove(tempAuction);
                addAuction(auction);
                return true;
            }
        }

        return false;
    }

    public  boolean updateIsAuctionOpen(UUID auctionId, boolean isAuctionOpen) {

        for (Auction auction : auctions) {
            if (auction.getAuctionId().equals(auctionId)) {
                auction.setAuctionOpen(isAuctionOpen);
                return true;
            }
        }

        return false;
    }

    public  boolean addUser(User user) {
        users.add(user);
        return true;
    }


    public  User getUser(UUID userId) {
        for (User user : users) {
            if(user.getUserId().equals(userId))
            {
                return  user;
            }
        }
        return null;
    }

    public boolean updateUser(User user) {

        for (User tempUser : users) {
            if(tempUser.getUserId().equals(user.getUserId())) {
                users.remove(tempUser);
                users.add(user);
                return true;
            }
        }

       return false;
    }

}