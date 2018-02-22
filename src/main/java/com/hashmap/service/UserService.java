package com.hashmap.service;

import com.hashmap.core.User.UserStatus;
import com.hashmap.dao.InMemoryDao;
import com.hashmap.dao.InMemoryDAOImpl;
import com.hashmap.models.user.User;

import java.util.UUID;

public class UserService {
    private InMemoryDao inMemoryDao;

    public UserService() {
        inMemoryDao = new InMemoryDAOImpl();
    }


    public UserStatus createUser(User user) {

        if (user != null)
            if (inMemoryDao.addUser(user))
                return UserStatus.USER_ADDED;

        return UserStatus.USER_NOT_ADDED;

    }

    public User getUser(UUID userId) {
        return inMemoryDao.getUser(userId);
    }

}
