package com.hashmap.service;

import com.hashmap.core.User.UserStatus;
import com.hashmap.dao.InMemoryDoa;
import com.hashmap.dao.InMemoryDAOImpl;
import com.hashmap.models.user.User;

import java.util.UUID;

public class UserService {
    private InMemoryDoa dataAccessLayer;

    public UserService() {
        dataAccessLayer = new InMemoryDAOImpl();
    }


    public UserStatus createUser(User user) {

        if (user != null)
            if (dataAccessLayer.addUser(user))
                return UserStatus.USER_ADDED;

        return UserStatus.USER_NOT_ADDED;

    }

    public User getUser(UUID userId) {
        return dataAccessLayer.getUser(userId);
    }

}
