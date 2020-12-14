package com.aeradron.enigma.service.dao;

import com.aeradron.enigma.datasource.entity.Image;
import com.aeradron.enigma.datasource.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ImageDAOImpl implements ImageDAO {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public UUID saveImage(Image image) {
        return imageRepository.save(image).getUuid();
    }

    @Override
    public Optional<Image> retrieveImage(UUID id) {
        return imageRepository.findByUuid(id);
    }
}
