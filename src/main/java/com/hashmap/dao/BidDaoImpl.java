package com.hashmap.dao;

import com.hashmap.entity.auction.BidEntity;
import com.hashmap.models.Bid;
import com.hashmap.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BidDaoImpl implements BidDao {

    @Autowired
    private BidRepository bidRepository;

    @Override
    public Bid save(Bid bid) {

        BidEntity bidEntity = bidRepository.save(new BidEntity(bid));
        return bidEntity.toData();
    }
}
