package com.hashmap.service;

import com.hashmap.dao.UserDao;
import com.hashmap.dao.UserDaoImpl;
import com.hashmap.dao.WalletDao;
import com.hashmap.models.User;
import com.hashmap.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
 public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private WalletDao walletDao;

    UserService() {
        userDao = new UserDaoImpl();
    }

    public User addUser(User user) {

        user = userDao.addUser(user);
        createWallet(user.getUserId());

        return  user;
    }

    public Optional<User> getUser(UUID userId) {
        return userDao.getUser(userId);
    }

    private Wallet createWallet(UUID userId){

            Wallet wallet = new Wallet(userId, new BigDecimal(0));
            return  walletDao.save(wallet);

    }

}
