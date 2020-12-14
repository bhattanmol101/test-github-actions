package com.aeradron.enigma.service;

import com.aeradron.enigma.datasource.entity.Image;
import com.aeradron.enigma.service.dao.ImageDAO;
import com.aeradron.enigma.service.utility.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    ImageDAO imageDAO;

    @Override
    public UUID saveImage(MultipartFile image) throws Exception {
        Image img = new Image(image.getOriginalFilename(), image.getContentType(),
                UtilityService.compressBytes(image.getBytes()));
        img.setUuid(UUID.randomUUID());
        return imageDAO.saveImage(img);
    }

    @Override
    public Image retrieveImage(UUID imageUuid) throws Exception {
        Optional<Image> img = imageDAO.retrieveImage(imageUuid);
        Image image = new Image(img.get().getName(),img.get().getType(),
                UtilityService.decompressBytes(img.get().getImage()));
        return image;
    }
}
