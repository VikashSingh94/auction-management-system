package com.hashmap.dao;

import com.hashmap.entity.user.UserEntity;
import com.hashmap.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    User addUser(User user);
    Optional<User> getUser(UUID userId);

}
