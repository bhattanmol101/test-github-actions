package com.aeradron.enigma.datasource.repository;

import com.aeradron.enigma.datasource.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByUuid(UUID uuid);
}
