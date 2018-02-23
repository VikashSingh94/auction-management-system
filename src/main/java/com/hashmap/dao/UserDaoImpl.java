package com.hashmap.dao;

import com.hashmap.dbms.InMemoryDataBase;
import com.hashmap.exception.InvalidUser;
import com.hashmap.models.user.User;

import java.math.BigDecimal;
import java.util.UUID;

public class UserDaoImpl implements UserDao {
    private InMemoryDataBase inMemoryDataBase = InMemoryDataBase.getInstance();

    @Override
    public boolean addUser(User user) {
        return inMemoryDataBase.addUser(user);
    }

    @Override
    public User getUser(UUID userId) {
        return inMemoryDataBase.getUser(userId);
    }

    @Override
    public boolean updateTotalBalanced(UUID userId, BigDecimal amount) {
        return inMemoryDataBase.updateTotalBalanced(userId, amount);
    }

    @Override
    public BigDecimal totalBalanceInWallet(UUID userId) throws InvalidUser {
        User user = inMemoryDataBase.getUser(userId);

        if (user != null)
            return user.getWallet().getTotalBalanceInWallet();
        else
            throw new InvalidUser("User not present");
    }
}
