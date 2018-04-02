package com.hashmap.service;

import com.hashmap.dao.*;
import com.hashmap.exception.InvalidAuctionException;
import com.hashmap.models.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AuctionService {

    @Autowired
    private AuctionDao auctionDao;

    @Autowired
    private SchedulerService schedulerService;


    AuctionService() {

    }

    public Auction addAuction(Auction auction) {
        return auctionDao.save(auction);
    }

    public List<Auction> getOpenAuctions() {
        return auctionDao.getRunningAuctions();
    }

    public Optional<Auction> getAuction(UUID auctionId) {
        return auctionDao.getAuction(auctionId);
    }


    public void startAuctionTimer(UUID auctionId) {

        Optional<Auction> auction = auctionDao.getAuction(auctionId);

        if(auction.isPresent()) {
            markAuctionAsOpen(auction.get());
            schedulerService.startTimer(auction.get().getEndTime(),auctionId);
        }
        else {
            throw  new InvalidAuctionException("Auction " + auctionId + " not present");
        }

    }

    private void markAuctionAsOpen(Auction auction) {
        auction.setAuctionOpen(true);
        auctionDao.save(auction);
    }

}
