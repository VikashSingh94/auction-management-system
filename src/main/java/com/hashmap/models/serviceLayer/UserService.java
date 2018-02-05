package com.hashmap.models.serviceLayer;

import com.hashmap.models.dataAccessLayer.DataAccessLayer;
import com.hashmap.models.dataAccessLayer.InMemoryDAO;
import com.hashmap.models.user.User;

import java.util.UUID;

public class UserService
{
    DataAccessLayer dataAccessLayer;

    public UserService()
    {
        dataAccessLayer = new InMemoryDAO();
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
