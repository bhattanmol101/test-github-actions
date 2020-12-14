package com.aeradron.enigma.service;

import com.aeradron.enigma.datasource.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImageService {

    UUID saveImage(MultipartFile image) throws Exception;

    Image retrieveImage(UUID imageUuid) throws Exception;
}
