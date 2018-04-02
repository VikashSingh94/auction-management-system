package com.hashmap.service;

import com.hashmap.dao.WalletDao;
import com.hashmap.exception.InsufficientFundsException;
import com.hashmap.exception.InvalidUserException;
import com.hashmap.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

//TODO: Use Synchronization here
@Service
public  class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletDao walletDao;

    WalletServiceImpl() {

    }

    @Override
    public BigDecimal pay(UUID payerId, UUID payeeId, BigDecimal amount) {

        if (checkSufficientBalance(payerId, amount)) {

            debit(payerId, amount);
            credit(payeeId, amount);

            return  amount;
        }
        else
            throw new InsufficientFundsException("not sufficient balance");
    }


    @Override
    public BigDecimal credit(UUID userId, BigDecimal amount) {

        Optional<Wallet> wallet = walletDao.getWallet(userId);

       if(wallet.isPresent()) {
           amount = amount.add(wallet.get().getTotalBalance());
           return walletDao.updateBalance(userId, amount);
       }
       else {
           throw new InvalidUserException("User with " + userId + " not present");
       }

    }


    @Override
    public BigDecimal debit(UUID payerId, BigDecimal amount) {

        BigDecimal totalBalance = walletDao.totalBalanceInWallet(payerId);

        totalBalance = totalBalance.subtract(amount);

        return walletDao.updateBalance(payerId, totalBalance);
    }


    @Override
    public boolean checkSufficientBalance(UUID userId, BigDecimal amount) {

        BigDecimal totalBalance = walletDao.totalBalanceInWallet(userId);

        if(totalBalance.compareTo(amount) >= 0)
            return true;
        else
            return false;
    }
}
