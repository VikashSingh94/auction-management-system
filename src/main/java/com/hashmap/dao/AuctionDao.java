package com.hashmap.dao;

import com.hashmap.models.Auction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface  AuctionDao {

    List getAuctions();

    Auction save(Auction auction);

    Auction update(Auction auction);
//
    Optional<Auction> getAuction(UUID auctionId);
//
    List<Auction> getRunningAuctions();

}
