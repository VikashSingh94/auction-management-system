package com.hashmap.models.dataAccessLayer;

import com.hashmap.models.auction.Auction;
import com.hashmap.models.storage.InMemoryDataBase;
import com.hashmap.models.user.User;

import java.util.List;
import java.util.UUID;

public class InMemoryDAO implements DataAccessLayer {

    private InMemoryDataBase inMemoryDataBase = InMemoryDataBase.getInstance();

    public List getAuctions() {
        return  inMemoryDataBase.getAuctions();
    }

    public boolean addAuction(Auction auction){
        return inMemoryDataBase.addAuction(auction);
    }

    public boolean updateIsAuctionOpen(UUID auctionId, boolean isAuctionOpen) {

        return inMemoryDataBase.updateIsAuctionOpen(auctionId,isAuctionOpen);
    }

    public Auction getAuction(UUID auctionId) {
        return inMemoryDataBase.getAuction(auctionId);
    }

    public boolean addUser(User user) {
        return inMemoryDataBase.addUser(user);
    }

    public User getUser(UUID userId) {
        return inMemoryDataBase.getUser(userId);
    }


}
