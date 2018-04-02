package com.hashmap.entity.auction;

import com.hashmap.models.Auction;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "auction")
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID auctionId;
    private UUID sellerId;
    private int endTime;
    private boolean isAuctionOpen;

    @OneToOne
    private BidEntity currentBidEntity;

    @Embedded
    private ItemEntity itemEntity;
    private BigDecimal openingAuctionPrice;

    protected AuctionEntity(){
    }

    //Remember always check Auto - generated id for Not null
    //because while updating particular field , it will generate new Id
    //hence creating new entity with id
    public AuctionEntity(Auction auction) {

        if(auction.getAuctionId() != null)
            this.auctionId = auction.getAuctionId();

        this.sellerId = auction.getUserId();
        this.endTime = auction.getEndTime();
        this.isAuctionOpen = auction.isAuctionOpen();
        this.openingAuctionPrice = auction.getOpeningAuctionPrice();
        this.itemEntity = new ItemEntity(auction.getItem());

        if(auction.getCurrentBid() == null)
            this.currentBidEntity = null;
        else
            this.currentBidEntity = new BidEntity(auction.getCurrentBid());
    }

    public Auction toData(){
        Auction auction = new Auction();

        auction.setAuctionId(this.auctionId);
        auction.setUserId(this.sellerId);
        auction.setEndTime(this.endTime);
        auction.setAuctionOpen(this.isAuctionOpen);

        if (this.currentBidEntity != null) {
            auction.setCurrentBid(this.currentBidEntity.toData());
        }

        auction.setItem(this.itemEntity.toData());
        auction.setOpeningAuctionPrice(this.openingAuctionPrice);

        return auction;
    }


    public UUID getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(UUID auctionId) {
        this.auctionId = auctionId;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public boolean isAuctionOpen() {
        return isAuctionOpen;
    }

    public void setAuctionOpen(boolean auctionOpen) {
        isAuctionOpen = auctionOpen;
    }

    public BidEntity getCurrentBidEntity() {
        return currentBidEntity;
    }

    public void setCurrentBidEntity(BidEntity currentBidEntity) {
        this.currentBidEntity = currentBidEntity;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public BigDecimal getOpeningAuctionPrice() {
        return openingAuctionPrice;
    }

    public void setOpeningAuctionPrice(BigDecimal openingAuctionPrice) {
        this.openingAuctionPrice = openingAuctionPrice;
    }

}
