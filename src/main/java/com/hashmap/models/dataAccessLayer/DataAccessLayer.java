package com.hashmap.models.dataAccessLayer;


import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;
import com.hashmap.models.user.User;

import java.util.List;
import java.util.UUID;

public interface DataAccessLayer
{
    List getAuctions();

    boolean addAuction(Auction auction);

    boolean updateIsAuctionOpen(UUID auctionId, boolean isAuctionOpen);

    Auction getAuction(UUID auctionId);

    boolean addUser(User user);

    User getUser(UUID userId);
}