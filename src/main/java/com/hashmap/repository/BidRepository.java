package com.hashmap.repository;

import com.hashmap.entity.auction.BidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BidRepository extends JpaRepository<BidEntity,UUID> {

}
