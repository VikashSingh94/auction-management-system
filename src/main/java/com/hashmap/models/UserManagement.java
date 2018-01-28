package com.hashmap.models;

import java.util.UUID;

public class UserManagement {

    public void createUser(User user) {
        DataBase.addUser(user);
    }

    public User readUser(UUID userId) {
        try
        {
            return DataBase.getUser(userId);
        }
        catch (Exception exception)
        {
            System.out.println(exception);
        }
        return null;
    }
}
