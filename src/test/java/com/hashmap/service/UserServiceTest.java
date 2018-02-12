package com.hashmap.service;

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
        Assert.assertEquals(userService.createUser(user),Status.USER_ADDED);
    }

    @Test
    public void testCreateUserFailure()
    {
        Assert.assertEquals(userService.createUser(null),Status.USER_NOT_ADDED);
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
