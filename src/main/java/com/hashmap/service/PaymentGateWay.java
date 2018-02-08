package com.hashmap.service;

import com.hashmap.models.auction.Bid;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentGateWay {

    Status add(UUID userId, BigDecimal amount);

    Status pay(UUID payerId,UUID payeeId,BigDecimal amount);

    Status checkSufficientBalance(UUID userId, BigDecimal amount);

}
