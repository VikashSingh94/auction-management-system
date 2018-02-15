package com.hashmap.dao;


import com.hashmap.exception.InvalidAuction;
import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;
import com.hashmap.models.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface InMemoryDoa
{
    List getAuctions();

    boolean addAuction(Auction auction);

    boolean updateIsAuctionOpen(UUID auctionId, boolean isAuctionOpen);

    boolean updateCurrentBid(UUID auctionId , Bid bid);

    Auction getAuction(UUID auctionId)throws InvalidAuction;

    boolean addUser(User user);

    User getUser(UUID userId);


    boolean updateTotalBalanced(UUID userId, BigDecimal amount);

    List<Auction> getRunningAuction();
}