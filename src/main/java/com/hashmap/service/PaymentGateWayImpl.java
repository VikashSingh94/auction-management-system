package com.hashmap.service;

import com.hashmap.core.Auction.AmountStatus;
import com.hashmap.core.Payment.BalanceStatus;
import com.hashmap.core.Payment.PaymentStatus;
import com.hashmap.dao.InMemoryDAOImpl;
import com.hashmap.dao.InMemoryDao;
import com.hashmap.exception.InvalidUser;
import java.math.BigDecimal;
import java.util.UUID;

public class PaymentGateWayImpl implements PaymentGateWay {

    private InMemoryDao inMemoryDao;

    public PaymentGateWayImpl() {
        inMemoryDao = new InMemoryDAOImpl();
    }

    @Override
    public AmountStatus add(UUID userId, BigDecimal amount)throws InvalidUser {

        amount = amount.add(inMemoryDao.totalBalanceInWallet(userId));

        if (inMemoryDao.updateTotalBalanced(userId, amount))
            return AmountStatus.AMOUNT_ADDED;
        else
            return AmountStatus.AMOUNT_NOT_ADDED;
    }

    @Override
    public PaymentStatus pay(UUID payerId, UUID payeeId, BigDecimal amount)throws InvalidUser {

        if (checkSufficientBalance(payerId, amount).equals(BalanceStatus.SUFFICIENT_BALANCE)) {
            BigDecimal totalBalance = inMemoryDao.totalBalanceInWallet(payerId);

            if (inMemoryDao.updateTotalBalanced(payerId, totalBalance.subtract(amount)))
                if (add(payeeId, amount).equals(AmountStatus.AMOUNT_ADDED))
                    return PaymentStatus.PAYMENT_SUCCESSFUL;
        }
        return PaymentStatus.PAYMENT_NOT_SUCCESSFUL;

    }

    @Override
    public BalanceStatus checkSufficientBalance(UUID userId, BigDecimal amount)throws InvalidUser {

        BigDecimal totalBalance = inMemoryDao.totalBalanceInWallet(userId);

        if (totalBalance.compareTo(amount) >= 0)
            return BalanceStatus.SUFFICIENT_BALANCE;
        else
            return BalanceStatus.NOT_SUFFICIENT_BALANCE;

    }

}
