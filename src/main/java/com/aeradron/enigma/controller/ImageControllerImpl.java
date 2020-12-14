package com.aeradron.enigma.controller;

import com.aeradron.enigma.datasource.entity.Image;
import com.aeradron.enigma.service.ImageService;
import com.aeradron.enigma.service.exceptions.InternalException;
import com.aeradron.enigma.service.exceptions.MandatoryFieldMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
public class ImageControllerImpl implements ImageController{

    @Autowired
    ImageService imageService;

    @Override
    public ResponseEntity<UUID> saveImage(MultipartFile imageFile) {
        if (imageFile != null) {
            try {
                UUID id = imageService.saveImage(imageFile);
                return ResponseEntity.ok(id);
            } catch (Exception e) {
                throw new InternalException(e.getMessage());
            }
        } else {
            throw new MandatoryFieldMissingException("Required field Image File is missing!!");
        }
    }

    @Override
    public Image getImage(String imageUuid) {
        if (imageUuid != null) {
            try {
                return imageService.retrieveImage(UUID.fromString(imageUuid));
            } catch (Exception e) {
                throw new InternalException(e.getMessage());
            }
        } else {
            throw new MandatoryFieldMissingException("Required field Image Id is missing!!");
        }
    }
}
