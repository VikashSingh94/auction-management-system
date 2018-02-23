package com.hashmap.dao;

import com.hashmap.dbms.InMemoryDataBase;
import com.hashmap.exception.InvalidAuction;
import com.hashmap.models.auction.Auction;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

public class AuctionDaoImpl implements AuctionDao {

    private InMemoryDataBase inMemoryDataBase = InMemoryDataBase.getInstance();

    @Override
    public List getAuctions() {
        return inMemoryDataBase.getAuctions();
    }

    @Override
    public boolean addAuction(Auction auction) {
        return inMemoryDataBase.addAuction(auction);
    }

    @Override
    public boolean updateIsAuctionOpen(UUID auctionId, boolean isAuctionOpen) {
        return inMemoryDataBase.updateIsAuctionOpen(auctionId, isAuctionOpen);
    }

    @Override
    public Auction getAuction(UUID auctionId) throws InvalidAuction {

        Auction auction = inMemoryDataBase.getAuction(auctionId);

        if (auction == null)
            throw new InvalidAuction("Auction is not present");
        else
            return auction;

    }

    @Override
    public List<Auction> getRunningAuctions() {
        List<Auction> auctions = inMemoryDataBase.getAuctions();
        return auctions.stream().filter(auction -> (auction.getIsAuctionOpen())).collect(toList());
    }

}
