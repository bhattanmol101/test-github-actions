package com.aeradron.enigma.service.dao;

import com.aeradron.enigma.datasource.entity.User;
import com.aeradron.enigma.datasource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDAOImpl implements UserDAO {

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User getUserByUUID(UUID uuid) {
        return userRepository.findByUuid(uuid);
    }

    @Override
    public int deleteUserByUUID(UUID uuid) {
        return userRepository.deleteByUuid(uuid);
    }
}
