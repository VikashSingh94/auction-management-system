package com.hashmap.service;

import com.hashmap.core.Auction.AmountStatus;
import com.hashmap.core.Payment.BalanceStatus;
import com.hashmap.core.Payment.PaymentStatus;


import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentGateWay {

    AmountStatus add(UUID userId, BigDecimal amount);

    PaymentStatus pay(UUID payerId, UUID payeeId, BigDecimal amount);

    BalanceStatus checkSufficientBalance(UUID userId, BigDecimal amount);

}
