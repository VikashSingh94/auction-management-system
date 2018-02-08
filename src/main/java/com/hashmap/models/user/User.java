package com.hashmap.models.user;

import java.util.UUID;

public class User {
    private UUID userId;
    private String userName;
    private String contactDetails;
    private Wallet wallet;

    public User(String userName, String contactDetails) {
        this.userId = UUID.randomUUID();
        this.userName = userName;
        this.contactDetails = contactDetails;
        this.wallet = new Wallet();

    }

    public Wallet getWallet() {
        return wallet;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getContactDetails() {
        return contactDetails;
    }
}
