package com.hashmap.service;

import com.hashmap.core.Auction.AmountStatus;
import com.hashmap.core.Payment.PaymentStatus;
import com.hashmap.exception.InvalidUser;
import com.hashmap.models.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentGateWayTest {

    private PaymentGateWay paymentGateWay;
    private User seller;
    private User buyer;

    @Before
    public void beforeEachTestCase() {
        paymentGateWay = new PaymentGateWayImpl();

        seller = new User("abc", "abc@gmail.com");
        buyer = new User("xyz", "xyz@gmail.com");

        UserService userService = new UserService();
        userService.createUser(seller);
        userService.createUser(buyer);
    }

    @Test
    public void testAddSuccess() {
        BigDecimal amount = new BigDecimal(10);
        Assert.assertEquals(paymentGateWay.add(buyer.getUserId(), amount), AmountStatus.AMOUNT_ADDED);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testAddFailure() {
        expectedEx.expect(InvalidUser.class);
        expectedEx.expectMessage("User not present");

        BigDecimal amount = new BigDecimal(10);
        Assert.assertEquals(paymentGateWay.add(UUID.randomUUID(), amount), AmountStatus.AMOUNT_NOT_ADDED);
    }

    @Test
    public void testPaySuccess() {
        paymentGateWay.add(buyer.getUserId(), new BigDecimal(1000));

        UUID payerId = buyer.getUserId();
        UUID payeeId = seller.getUserId();
        BigDecimal amount = new BigDecimal(200);

        Assert.assertEquals(paymentGateWay.pay(payerId,payeeId,amount), PaymentStatus.PAYMENT_SUCCESSFUL);
    }

    @Test
    public void testPayFailure() {
        UUID payerId = buyer.getUserId();
        UUID payeeId = seller.getUserId();
        BigDecimal amount = new BigDecimal(200);

        Assert.assertEquals(paymentGateWay.pay(payerId,payeeId,amount), PaymentStatus.PAYMENT_NOT_SUCCESSFUL);

    }
}
