package com.hashmap.models;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

public class UserManagementTest {

    @Test
    public void testcreateUser()
    {
        UserManagement userManagement = new UserManagement();
        Assert.assertNotNull(userManagement);

        User user = new User("abc","abc@gmail.com");
        userManagement.createUser(user);

        Assert.assertNotNull(userManagement.readUser(user.getUserId()));
    }
}
