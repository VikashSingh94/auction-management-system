package com.hashmap.service;

import com.hashmap.core.User.UserStatus;
import com.hashmap.dao.UserDao;
import com.hashmap.dao.UserDaoImpl;
import com.hashmap.models.user.User;

import java.util.UUID;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDaoImpl();
    }


    public UserStatus createUser(User user) {

        if (user != null)
            if (userDao.addUser(user))
                return UserStatus.USER_ADDED;

        return UserStatus.USER_NOT_ADDED;

    }

    public User getUser(UUID userId) {
        return userDao.getUser(userId);
    }

}
