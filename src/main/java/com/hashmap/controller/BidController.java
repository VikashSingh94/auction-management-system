package com.hashmap.controller;

import com.hashmap.models.Bid;
import com.hashmap.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class BidController {

    @Autowired
    private BidService bidService;

    //refactor the rest api here
    @RequestMapping(method = {RequestMethod.POST}, value = "/auctions/{auctionId}/bids")
    Bid placeBid(@PathVariable UUID auctionId, @RequestBody Bid bid) {
        return bidService.placeBid(auctionId, bid);
    }
}
