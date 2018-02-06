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


    public String createUser(User user) {

        if(user!=null)
            if(dataAccessLayer.addUser(user))
                return "user added";

        return "user not added";

    }

    public User getUser(UUID userId)
    {
        return dataAccessLayer.getUser(userId);
    }

}