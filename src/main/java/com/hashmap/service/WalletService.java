package com.hashmap.service;

import java.math.BigDecimal;
import java.util.UUID;

 public interface WalletService {

    BigDecimal credit(UUID userId, BigDecimal amount);

    BigDecimal pay(UUID payerId, UUID payeeId, BigDecimal amount);

    boolean checkSufficientBalance(UUID userId, BigDecimal amount);

    BigDecimal debit(UUID userId, BigDecimal amount);
}
