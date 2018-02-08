package com.hashmap.models;

import com.hashmap.models.user.Wallet;
import org.junit.Assert;
import org.junit.Test;

public class WalletTest {

    @Test
    public void testWallet() {

        Wallet wallet = new Wallet();
        Assert.assertNotNull(wallet.getTotalBalanceInWallet());

    }
}