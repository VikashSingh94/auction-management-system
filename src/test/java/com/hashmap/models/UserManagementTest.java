package com.hashmap.models;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

public class UserManagementTest {


/*
    @BeforeClass
    public static void UserManager()
    {
        UserManagement userManagement;
        userManagement = new UserManagement();
    }
    */

    @Test
    public void testcreateUser()
    {
        UserManagement userManagement = new UserManagement();
        Assert.assertNotNull(userManagement);

        User user = new User("abc","abc@gmail.com");
        userManagement.createUser(user);

        UUID userId = UUID.randomUUID();
        userManagement.readUser(userId);
    }
}
