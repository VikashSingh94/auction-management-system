package com.hashmap.dao;

import com.hashmap.models.auction.Auction;
import java.util.List;
import java.util.UUID;

public interface  AuctionDao {

    List getAuctions();

    boolean addAuction(Auction auction);

    boolean updateIsAuctionOpen(UUID auctionId, boolean isAuctionOpen);

    Auction getAuction(UUID auctionId);

    List<Auction> getRunningAuctions();

}
