package com.hashmap.service;

import com.hashmap.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class UserServiceTest {


    private User user;

    @Autowired
    private UserService userService;


    @Before
    public void setUp(){
        user = new User("abc", "abc@gmail.com");
    }

    @Test
    public void testAddUser() {
        Assert.assertNotNull(userService.addUser(user));
    }

    @Test(expected=NullPointerException.class)
    public void testAddNullUser() {
        Assert.assertNull(userService.addUser(null));
    }

    @Test
    public void testGetUserByUnknownId()  {
        UUID userId = UUID.randomUUID();
        Assert.assertEquals(userService.getUser(userId), Optional.empty());
    }

    @Test
    public void testGetUser(){
        user = userService.addUser(user);
        Assert.assertNotNull(userService.getUser(user.getUserId()));
    }
}
