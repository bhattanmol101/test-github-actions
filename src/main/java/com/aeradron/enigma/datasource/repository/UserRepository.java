package com.aeradron.enigma.datasource.repository;

import com.aeradron.enigma.datasource.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUuid(UUID uuid);

    int deleteByUuid(UUID uuid);
}
