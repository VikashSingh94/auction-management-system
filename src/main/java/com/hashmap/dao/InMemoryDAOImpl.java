package com.hashmap.dao;

import com.hashmap.exception.InvalidAuction;
import com.hashmap.exception.InvalidUser;
import com.hashmap.models.auction.Auction;
import com.hashmap.dbms.*;
import com.hashmap.models.auction.Bid;
import com.hashmap.models.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

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
    public Auction getAuction(UUID auctionId)throws InvalidAuction {

        Auction auction = inMemoryDataBase.getAuction(auctionId);

        if(auction == null)
            throw new InvalidAuction("Auction is not present");
        else
            return auction;

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

    @Override
    public List<Auction> getRunningAuction() {
        List<Auction> auctions = inMemoryDataBase.getAuctions();
        return auctions.stream().filter(auction ->(auction.getIsAuctionOpen())).collect(toList());
    }

    @Override
    public BigDecimal getTotalBalanceInWallet(UUID userId) throws InvalidUser{
        User user = inMemoryDataBase.getUser(userId);

        if( user != null)
            return user.getWallet().getTotalBalanceInWallet();
        else
            throw new InvalidUser("User not present");
    }
}
