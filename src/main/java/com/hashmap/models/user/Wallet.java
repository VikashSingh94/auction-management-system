package com.hashmap.models.user;

import java.math.BigDecimal;

public class Wallet {

    private BigDecimal totalBalance;

    public Wallet() {
        this.totalBalance = new BigDecimal(0);
    }

    public BigDecimal getTotalBalanceInWallet() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalanceInWallet) {
        this.totalBalance = totalBalanceInWallet;
    }
}
