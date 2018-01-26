package com.hashmap.models;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void testCreateUser()
    {
        User user = new User("abc","abc@gmail.com");

        Assert.assertNotNull(user.getUserId());
        Assert.assertNotNull(user.getUserName());
        Assert.assertNotNull(user.getContactDetails());
    }
}

