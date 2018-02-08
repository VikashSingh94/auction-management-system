package com.hashmap.dao;

import com.hashmap.models.auction.Auction;
import com.hashmap.dbms.*;
import com.hashmap.models.auction.Bid;
import com.hashmap.models.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class InMemoryDAOImpl implements InMemoryDoa {


    private InMemoryDataBase inMemoryDataBase = InMemoryDataBase.getInstance();

    @Override
    public List getAuctions() {
        return  inMemoryDataBase.getAuctions();
    }

    @Override
    public boolean addAuction(Auction auction){
        return inMemoryDataBase.addAuction(auction);
    }

    @Override
    public boolean updateIsAuctionOpen(UUID auctionId, boolean isAuctionOpen) {
        return inMemoryDataBase.updateIsAuctionOpen(auctionId,isAuctionOpen);
    }

    @Override
    public boolean updateCurrentBid(UUID auctionId,Bid bid) {
        return inMemoryDataBase.updateCurrentBid(auctionId,bid);
    }

    @Override
    public Auction getAuction(UUID auctionId) {
        return inMemoryDataBase.getAuction(auctionId);
    }



    @Override
    public boolean addUser(User user) {
        return inMemoryDataBase.addUser(user);
    }
    @Override
    public User getUser(UUID userId) {
        return inMemoryDataBase.getUser(userId);
    }

    @Override
    public boolean updateTotalBalanced(UUID userId, BigDecimal amount) {
        return inMemoryDataBase.updateTotalBalanced(userId,amount);
    }
}
