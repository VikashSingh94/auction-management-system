package com.hashmap.models;

import org.junit.Assert;
import org.junit.Test;

public class WalletTest {

    @Test
    public void testCreateWallet() {

        Wallet wallet = new Wallet();
        Assert.assertNotNull(wallet.getWalletId());
        Assert.assertNotNull(wallet.getTotalBalanceInWallet());

    }
}