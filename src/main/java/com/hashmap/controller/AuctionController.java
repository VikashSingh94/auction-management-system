package com.hashmap.controller;

import com.hashmap.exception.InvalidAuctionException;
import com.hashmap.exception.InvalidUserException;
import com.hashmap.models.Auction;
import com.hashmap.service.AuctionService;
import com.hashmap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
public class AuctionController {

    @Autowired
    private AuctionService auctionService;
    @Autowired
    private UserService userService;


    @RequestMapping(method = {RequestMethod.PUT}, value = "/auctions/{userId}")
    Auction addAuction(@RequestBody Auction auction , @PathVariable  UUID userId) {

        if(isUserPresent(userId)) {
            auction.setUserId(userId);
            return auctionService.addAuction(auction);
        }
        throw new InvalidUserException("user with " + userId + " does not exist");
    }

    @RequestMapping(method = {RequestMethod.GET},value = "/auctions/{auctionId}")
    Optional<Auction> getAuction(@PathVariable UUID auctionId){

        if(checkNotNull(auctionId)) {
            return auctionService.getAuction(auctionId);
        }
        throw new InvalidAuctionException("auctionId can't be null");
    }

    @RequestMapping(method = {RequestMethod.POST}, value = "/auctions/{auctionId}")
    String startAuction(@PathVariable UUID auctionId){

        auctionService.startAuctionTimer(auctionId);
        return "Auction started";

    }

    private boolean isUserPresent(UUID userId){
        return userService.getUser(userId).isPresent();
    }

    private <T> boolean checkNotNull(T reference) {
        return !(reference == null);
    }

}
