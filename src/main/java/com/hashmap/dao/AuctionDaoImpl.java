package com.hashmap.dao;

import com.hashmap.entity.auction.AuctionEntity;
import com.hashmap.models.Auction;
import com.hashmap.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;


@Component
public class AuctionDaoImpl implements AuctionDao  {

    @Autowired
    private AuctionRepository auctionRepository;

    @Override
    public List getAuctions() {
        return auctionRepository.findAll();
    }

    @Override
    public Auction save(Auction auction) {
        AuctionEntity auctionEntity = new AuctionEntity(auction);
        return auctionRepository.save(auctionEntity).toData();
    }

        @Override
    public Optional<Auction> getAuction(UUID auctionId){
        Optional<AuctionEntity> auctionEntity = auctionRepository.findById(auctionId);

        if (auctionEntity.isPresent()) {
            return Optional.of(auctionEntity.get().toData());
        } else {
            return Optional.empty();
        }

    }

    @Override
    public Auction update(Auction auction) {
        AuctionEntity auctionEntity =  auctionRepository.save(new AuctionEntity(auction));
        return auctionEntity.toData();
    }



    @Override
    public List<Auction> getRunningAuctions() {

        List<AuctionEntity> auctionEntities = auctionRepository.findAll();
        List<Auction> auctions = auctionEntities.stream().map(auction -> auction.toData()).collect(toList());

        return auctions.stream().filter(auction -> (auction.isAuctionOpen())).collect(toList());
    }

}
