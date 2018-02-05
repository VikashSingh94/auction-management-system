package com.hashmap.models.user;

import java.util.UUID;

public class User {
    private UUID userId;
    private String userName;
    private String contactDetails;

    public User(String userName, String contactDetails) {
        this.userId = UUID.randomUUID();
        this.userName = userName;
        this.contactDetails = contactDetails;
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
