package com.hashmap.controller;

import com.hashmap.exception.InvalidAuctionException;
import com.hashmap.models.User;
import com.hashmap.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    @RequestMapping(method = {RequestMethod.PUT}, value = "/users")
    public User addEmployee(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequestMapping(method = {RequestMethod.GET},value = "/users/{userId}")
    Optional<User> getAuction(@PathVariable UUID userId){

        if(checkNotNull(userId)) {
            return userService.getUser(userId);
        }
        throw new InvalidAuctionException("user  "+ " does not exist");
    }


    @RequestMapping(method = {RequestMethod.POST}, value = "/wallets/{userId}")
    public BigDecimal credit(@RequestBody BigDecimal amount ,@PathVariable UUID userId){
        return walletService.credit(userId, amount);
    }

    private <T> boolean checkNotNull(T reference) {
        return !(reference == null);
    }

}


