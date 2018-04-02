package com.hashmap.repository;

import com.hashmap.entity.user.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<WalletEntity,UUID> {
}
