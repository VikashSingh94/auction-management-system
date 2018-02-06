package com.hashmap.dao;


import com.hashmap.models.auction.Auction;
import com.hashmap.models.user.User;

import java.util.List;
import java.util.UUID;

public interface InMemoryDoa
{
    List getAuctions();

    boolean addAuction(Auction auction);

    boolean updateIsAuctionOpen(UUID auctionId, boolean isAuctionOpen);

    Auction getAuction(UUID auctionId);

    boolean addUser(User user);

    User getUser(UUID userId);
}