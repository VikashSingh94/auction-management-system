package com.hashmap.service;

import com.hashmap.core.Auction.AuctionStatus;
import com.hashmap.core.Auction.BidStatus;
import com.hashmap.core.Payment.BalanceStatus;
import com.hashmap.dao.*;
import com.hashmap.exception.InSufficientBalance;
import com.hashmap.exception.InvalidAuction;
import com.hashmap.exception.InvalidBid;
import com.hashmap.models.auction.Auction;
import com.hashmap.models.auction.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service
public class AuctionService {

    @Autowired
    private AuctionDao auctionDao;
    @Autowired
    private BidDao bidDao;
    @Autowired
    private PaymentGateWay paymentGateWay;

    private Map<UUID, TimerService> timers = new TreeMap<>();

    private AuctionService() {
    }

    public AuctionStatus addAuction(Auction auction) {

        if (auctionDao.addAuction(auction).equals(auction)) {
            startAuctionTimer(auction);
            return AuctionStatus.AUCTION_ADDED;
        }

        return AuctionStatus.AUCTION_NOT_ADDED;
    }

    private void startAuctionTimer(Auction auction) {
        Listener listener = new AuctionListener(auction.getAuctionId());
        timers.put(auction.getAuctionId(), new TimerService(auction.getEndTime(), listener));
    }

    public List<Auction> getOpenAuctions() {
        return auctionDao.getRunningAuctions();
    }

    public Optional<Auction> getAuction(UUID auctionId) {
        return auctionDao.getAuction(auctionId);
    }

    public BidStatus placeBid(UUID auctionId, Bid bid) {

        Optional<Auction> auction = auctionDao.getAuction(auctionId);

        if (auction.isPresent() && auction.get().isAuctionOpen()) {

            checkBalance(bid);
            isPresentBidGreaterThenCurrentBid(auction.get(), bid);

            if (updateCurrentBid(auction.get(), bid).equals(auction.get()))
                return BidStatus.BID_ADDED;
            else
                return BidStatus.BID_NOT_ADDED;
        }
        else
            throw new InvalidAuction("Auction is closed now ");
    }

    private void checkBalance(Bid bid) {
        UUID userId = bid.getUserId();
        BigDecimal bidPrice = bid.getBidPrice();

        if (paymentGateWay.checkSufficientBalance(userId, bidPrice).equals(BalanceStatus.NOT_SUFFICIENT_BALANCE))
            throw new InSufficientBalance("Not SufficientBalance");

    }

    private void isPresentBidGreaterThenCurrentBid(Auction auction, Bid bid) {
        BigDecimal currentBidPrice = auction.getCurrentBid().getBidPrice();
        BigDecimal presentBidPrice = bid.getBidPrice();

        if ((currentBidPrice.compareTo(presentBidPrice) >= 0)) {
            throw new InvalidBid("Bid price is lower than the current bid");
        }

    }

    private Auction updateCurrentBid(Auction auction, Bid bid) {
        auction.setCurrentBid(bid);
        return auctionDao.update(auction);
    }

}
