package com.hashmap.service;

import com.hashmap.dao.InMemoryDAOImpl;
import com.hashmap.dao.InMemoryDoa;
import com.hashmap.models.auction.Bid;
import com.hashmap.models.user.User;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentGateWayImpl implements PaymentGateWay {

    InMemoryDoa dataAccessLayer;

    public PaymentGateWayImpl(){
        dataAccessLayer = new InMemoryDAOImpl();
    }

    @Override
    public Status add(UUID userId, BigDecimal amount) {

        User user = dataAccessLayer.getUser(userId);
        if(user!=null) {
            amount = amount.add(dataAccessLayer.getUser(userId).getWallet().getTotalBalanceInWallet());

            if (dataAccessLayer.updateTotalBalanced(userId, amount))
                return Status.AMOUNT_ADDED;
            else
                return Status.AMOUNT_NOT_ADDED;
        }
        //TODO: rename to user not present
        else
            return Status.AMOUNT_NOT_ADDED;
    }

    @Override
    public Status pay( UUID payerId,UUID payeeId,BigDecimal amount) {

        if(checkSufficientBalance(payerId,amount).equals(Status.SUFFICIENT_BALANCE))
        {
            BigDecimal totalBalance = dataAccessLayer.getUser(payerId).getWallet().getTotalBalanceInWallet();

            if(dataAccessLayer.updateTotalBalanced(payerId,totalBalance.subtract(amount)))
                if(add(payeeId,amount).equals(Status.AMOUNT_ADDED))
                    return Status.PAYMENT_SUCCESSFUL;
        }
        return Status.PAYMENT_NOT_SUCCESSFUL;

    }

    @Override
    public Status checkSufficientBalance(UUID userId,BigDecimal amount) {

        BigDecimal totalBalance = dataAccessLayer.getUser(userId).getWallet().getTotalBalanceInWallet();

        if(totalBalance.compareTo(amount) >=0)
            return Status.SUFFICIENT_BALANCE;
        else
            return Status.NOT_SUFFICIENTBALANCE;

    }

}
