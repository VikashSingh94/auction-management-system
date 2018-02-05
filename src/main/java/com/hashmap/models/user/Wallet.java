package com.hashmap.models.user;

import java.math.BigDecimal;
import java.util.UUID;


public class Wallet {

    private BigDecimal totalBalanceInWallet;
    private UUID walletId;

    public Wallet(BigDecimal totalBalanceInWallet) {
        this.totalBalanceInWallet = totalBalanceInWallet;
        this.walletId = UUID.randomUUID();
    }

    public Wallet() {
        this.totalBalanceInWallet = new BigDecimal(0);
        this.walletId = UUID.randomUUID();
    }

    public BigDecimal getTotalBalanceInWallet() {
        return totalBalanceInWallet;
    }


    public UUID getWalletId() {
        return walletId;
    }
}
