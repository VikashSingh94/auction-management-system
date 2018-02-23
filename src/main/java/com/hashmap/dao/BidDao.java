package com.hashmap.dao;
import com.hashmap.models.auction.Bid;
import java.util.UUID;

public interface BidDao {
    boolean updateCurrentBid(UUID auctionId , Bid bid);
}
