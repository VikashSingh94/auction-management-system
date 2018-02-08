package com.hashmap.service;

import com.hashmap.service.UserService;
import com.hashmap.models.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;


public class UserServiceTest {

    User user;
    UserService userService;


    @Before
    public void beforeEachTestCase()
    {
        user = new User("abc", "abc@gmail.com");
        userService = new UserService();
    }


    @Test
    public void testCreateUserSuccess()
    {
        String  message = userService.createUser(user);
        Assert.assertEquals(message,"user added");
    }

    @Test
    public void testCreateUserFailure()
    {
        String  message = userService.createUser(null);
        Assert.assertEquals(message,"user not added");
    }

    @Test
    public void testGetUserSuccess()
    {
        userService.createUser(user);
        Assert.assertEquals(userService.getUser(user.getUserId()),user);
    }

    @Test
    public  void testGetUserFailure()
    {
        Assert.assertNull(userService.getUser(UUID.randomUUID()));
    }



}
