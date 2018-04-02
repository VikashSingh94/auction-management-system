package com.hashmap.entity.auction;

import com.hashmap.models.Bid;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "bid")
public class BidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bidId;

    private UUID userId;

    private UUID auctionId;

    private BigDecimal bidPrice;

    protected BidEntity(){

    }

    public BidEntity(Bid bid){

        if(bid.getBidId() != null) {
            this.bidId = bid.getBidId();
        }

        this.userId =  bid.getUserId();
        this.auctionId = bid.getAuctionId();
        this.bidPrice = bid.getBidPrice();
    }

    public Bid toData(){
        Bid bid = new Bid();
        bid.setBidId(this.bidId) ;
        bid.setUserId(this.userId);
        bid.setAuctionId(this.auctionId);
        bid.setBidPrice(this.bidPrice);

        return bid;
    }

    public UUID getBidId() {
        return bidId;
    }

    public void setBidId(UUID bidId) {
        this.bidId = bidId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(UUID auctionId) {
        this.auctionId = auctionId;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }
}


//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "auctionId")
//    private AuctionEntity auction;