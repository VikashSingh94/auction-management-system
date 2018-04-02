package com.hashmap.models;


import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {

    private UUID userId;
    private BigDecimal totalBalance;


    public Wallet(UUID userId, BigDecimal totalBalance){
        this.userId = userId;
        this.totalBalance = totalBalance;
    }


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }
}
