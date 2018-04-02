package com.hashmap.models;

import java.util.UUID;

public class User {

    private UUID userId;
    private String userName;
    private String contactDetails;

    User(){}

    public User(String userName, String contactDetails) {
        this.userName = userName;
        this.contactDetails = contactDetails;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}
