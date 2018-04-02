package com.hashmap.dao;

import com.hashmap.entity.user.UserEntity;
import com.hashmap.models.User;
import com.hashmap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {

        UserEntity userEntity = userRepository.save(new UserEntity(user));
        return userEntity.toData();
    }

    @Override
    public Optional<User> getUser(UUID userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        if (userEntity.isPresent()) {
            return Optional.of(userEntity.get().toData());
        } else {
            return Optional.empty();
        }
    }

}
