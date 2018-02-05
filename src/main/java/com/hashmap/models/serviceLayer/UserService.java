package com.hashmap.models.serviceLayer;

import com.hashmap.models.dataAccessLayer.DataAccessLayer;
import com.hashmap.models.dataAccessLayer.InMemoryDAO;
import com.hashmap.models.user.User;

import java.util.UUID;

public class UserService
{

    DataAccessLayer dataAccessLayer = new InMemoryDAO();

    public String createUser(User user) {
        if(dataAccessLayer.addUser(user))
            return "user not added";

        return "user added";
    }

    public User getUser(UUID userId) {
        return dataAccessLayer.getUser(userId);
    }
}
