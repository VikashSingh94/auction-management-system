package com.hashmap.repository;

import com.hashmap.entity.auction.AuctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionEntity,UUID>{

}
