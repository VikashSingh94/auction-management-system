package com.hashmap.entity.user;

import com.hashmap.models.User;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    private String userName;
    private String contactDetails;

    protected UserEntity(){}

    public UserEntity(String userName, String contactDetails) {

    }

    public User toData(){
       User user = new User(this.userName,this.contactDetails);
        user.setUserId(this.userId);
        return user;
    }

    public UserEntity(User user) {

        if(user.getUserId() != null) {
            this.userId = user.getUserId();
        }

        this.userName = user.getUserName();
        this.contactDetails = user.getContactDetails();
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
