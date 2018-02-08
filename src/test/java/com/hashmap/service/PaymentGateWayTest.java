package com.hashmap.service;

import com.hashmap.models.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentGateWayTest {

    PaymentGateWay paymentGateWay;
    User seller;
    User buyer;

    @Before
    public void beforeEachTestCase() {
        paymentGateWay = new PaymentGateWayImpl();

        seller = new User("abc", "abc@gmail.com");
        buyer  = new User("xyz","xyz@gmail.com");

        UserService userService = new UserService();
        userService.createUser(seller);
        userService.createUser(buyer);
    }

    @Test
    public void testAddSuccess() {
        BigDecimal amount = new BigDecimal(10);
        Assert.assertEquals(paymentGateWay.add(buyer.getUserId(), amount), Status.AMOUNT_ADDED);
    }

    @Test
    public void testAddFailure() {
        BigDecimal amount = new BigDecimal(10);
        Assert.assertEquals(paymentGateWay.add(UUID.randomUUID(), amount), Status.AMOUNT_NOT_ADDED);
    }

    @Test
    public void testPaySuccess()
    {
        paymentGateWay.add(seller.getUserId(),new BigDecimal(1000));
        Assert.assertEquals(paymentGateWay.pay(seller.getUserId(),buyer.getUserId(),new BigDecimal(200)),Status.PAYMENT_SUCCESSFUL);
    }

    @Test
    public void testPayFailure()
    {
        Assert.assertEquals(paymentGateWay.pay(seller.getUserId(),buyer.getUserId(),new BigDecimal(200)),Status.PAYMENT_NOT_SUCCESSFUL);

    }
}
