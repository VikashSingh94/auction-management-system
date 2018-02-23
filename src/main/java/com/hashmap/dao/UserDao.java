package com.hashmap.dao;

import com.hashmap.models.user.User;

import java.math.BigDecimal;
import java.util.UUID;

public interface UserDao {

    boolean addUser(User user);
    User getUser(UUID userId);
    boolean updateTotalBalanced(UUID userId, BigDecimal amount);
    BigDecimal totalBalanceInWallet(UUID userId);

}
