package com.hashmap.service;

import com.hashmap.core.Auction.AuctionStatus;
import com.hashmap.core.Auction.BidStatus;
import com.hashmap.core.Payment.BalanceStatus;
import com.hashmap.exception.InSufficientBalance;
import com.hashmap.exception.InvalidAuction;
import com.hashmap.exception.InvalidBid;
import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;
import com.hashmap.dao.InMemoryDoa;
import com.hashmap.dao.InMemoryDAOImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;


public class AuctionService {

    private InMemoryDoa inMemoryDoa;
    private PaymentGateWay paymentGateWay;
    private  Map<UUID, TimerService> timers = new TreeMap<>();


    //Reduce the dependency,Initialize in constructor,
    //such that all member function can access it.
    public AuctionService() {
        inMemoryDoa = new InMemoryDAOImpl();
        paymentGateWay = new PaymentGateWayImpl();
    }


    //Function should be doing one thing
    //So,we created function StartTimeWithListenerCallBack
    public AuctionStatus addAuction(Auction auction) {

        if (auction != null) {
            if (inMemoryDoa.addAuction(auction)) {
                startAuctionTimer(auction);
                return AuctionStatus.AUCTION_ADDED;
            }
        }
        return AuctionStatus.AUCTION_NOT_ADDED;
    }

    public void startAuctionTimer(Auction auction) {
        Listener listener = new AuctionListener(auction.getAuctionId());
        timers.put(auction.getAuctionId(), new TimerService(auction.getEndTimeInSeconds(), listener));
    }


    public List<Auction> getOpenAuctions() {
        return inMemoryDoa.getRunningAuction();
    }

    public Auction getAuction(UUID auctionId) {
        return inMemoryDoa.getAuction(auctionId);
    }


    //function should have one level of abstraction
    //refactor the placeBid method and create the 4 method

    public BidStatus placeBid(UUID auctionId, Bid bid)  {
        Auction auction = inMemoryDoa.getAuction(auctionId);

        checkBalance(bid);

        if (auction.getIsAuctionOpen())
            return updateCurrentBid(auction, bid);
        else
            throw new InvalidAuction("Auction is closed now ");
    }

    private void checkBalance(Bid bid) {
        UUID userId = bid.getUserId();
        BigDecimal bidPrice = bid.getBidPrice();

        if (paymentGateWay.checkSufficientBalance(userId, bidPrice).equals(BalanceStatus.NOT_SUFFICIENT_BALANCE))
            throw new InSufficientBalance("Not SufficientBalance");
    }

    private BidStatus updateCurrentBid(Auction auction, Bid bid) {
        BigDecimal currentBidPrice = auction.getCurrentBid().getBidPrice();
        BigDecimal presentBidPrice = bid.getBidPrice();

        if ((currentBidPrice.compareTo(presentBidPrice) < 0))
            return addBidToAuction(auction.getAuctionId(), bid);
        else
            throw new InvalidBid("Bid price is lower than the current bid");
    }

    private BidStatus addBidToAuction(UUID auctionId, Bid bid) {

        if (inMemoryDoa.updateCurrentBid(auctionId, bid))
            return BidStatus.BID_ADDED;
        else
            return BidStatus.BID_NOT_ADDED;
    }

}
