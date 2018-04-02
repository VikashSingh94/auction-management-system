package com.hashmap.dao;

import com.hashmap.models.Wallet;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface WalletDao {
    BigDecimal totalBalanceInWallet(UUID userId);
    BigDecimal updateBalance(UUID userId, BigDecimal amount);
    Optional<Wallet> getWallet(UUID userId);
    Wallet save(Wallet wallet);

}
