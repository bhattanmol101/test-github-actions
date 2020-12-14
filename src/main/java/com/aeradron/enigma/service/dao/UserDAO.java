package com.aeradron.enigma.service.dao;

import com.aeradron.enigma.datasource.entity.User;

import java.util.UUID;

public interface UserDAO {

    void saveUser(User user);

    void updateUser(User user);

    User getUserByUUID(UUID uuid);

    int deleteUserByUUID(UUID uuid);

}
