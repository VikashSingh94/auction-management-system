package com.hashmap.service;

import com.hashmap.exception.InsufficientFundsException;
import com.hashmap.exception.InvalidUserException;
import com.hashmap.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;

    private User seller;
    private User buyer;

    @Before
    public void setUp() {

        seller = new User("abc", "abc@gmail.com");
        buyer = new User("xyz", "xyz@gmail.com");

        seller = userService.addUser(seller);
        buyer = userService.addUser(buyer);

    }

    @Test
    public void testCreditToKnownUser() {
        BigDecimal amount = new BigDecimal(1000).setScale(2, BigDecimal.ROUND_HALF_UP);
        Assert.assertEquals(walletService.credit(seller.getUserId(), amount), amount);
    }


    @Test(expected = InvalidUserException.class)
    public void testCreditToUnknownUser() {
        BigDecimal amount = new BigDecimal(1000);
        walletService.credit(UUID.randomUUID(), amount);
    }


    @Test(expected = InsufficientFundsException.class)
    public void testPayWithZeroBalance() {

        UUID payerId = buyer.getUserId();
        UUID payeeId = seller.getUserId();
        BigDecimal amount = new BigDecimal(200).setScale(2, BigDecimal.ROUND_HALF_UP);

        walletService.pay(payerId, payeeId, amount);
    }

    @Test
    public void testPaySufficientBalance() {
        UUID payerId = buyer.getUserId();
        UUID payeeId = seller.getUserId();
        BigDecimal amount = new BigDecimal(200).setScale(2, BigDecimal.ROUND_HALF_UP);

        walletService.credit(buyer.getUserId(), new BigDecimal(1000));

        Assert.assertEquals(walletService.pay(payerId, payeeId, amount), amount);
    }

    @Test
    public void testCheckSufficientBalance(){
        walletService.credit(buyer.getUserId(), new BigDecimal(1000));

        Assert.assertTrue(walletService.checkSufficientBalance(buyer.getUserId(), new BigDecimal(200)));
    }
}
