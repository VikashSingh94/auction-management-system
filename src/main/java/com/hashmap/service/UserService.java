package com.hashmap.service;

import com.hashmap.dao.InMemoryDoa;
import com.hashmap.dao.InMemoryDAOImpl;
import com.hashmap.models.user.User;

import java.util.UUID;

public class UserService
{
    InMemoryDoa dataAccessLayer;

    public UserService()
    {
        dataAccessLayer = new InMemoryDAOImpl();
    }


    public Status createUser(User user) {

        if(user!=null)
            if(dataAccessLayer.addUser(user))
                return Status.USER_ADDED;

        return Status.USER_NOT_ADDED;

    }

    public User getUser(UUID userId)
    {
        return dataAccessLayer.getUser(userId);
    }

}
