package com.aeradron.enigma.service.dao;

import com.aeradron.enigma.datasource.entity.Image;
import java.util.Optional;
import java.util.UUID;

public interface ImageDAO {

    UUID saveImage(Image image);

    Optional<Image> retrieveImage(UUID id);
}
