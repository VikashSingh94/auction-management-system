package com.hashmap.dao;

import com.hashmap.dbms.InMemoryDataBase;
import com.hashmap.models.auction.Bid;

import java.util.UUID;

public class BidDaoImpl implements BidDao {

    private InMemoryDataBase inMemoryDataBase = InMemoryDataBase.getInstance();

    @Override
    public boolean updateCurrentBid(UUID auctionId, Bid bid) {
        return inMemoryDataBase.updateCurrentBid(auctionId, bid);
    }
}
