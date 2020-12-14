package com.aeradron.enigma.controller;

import com.aeradron.enigma.datasource.entity.Image;
import com.aeradron.enigma.service.utility.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@Api(tags = {"Image-API"})
@RequestMapping(Constants.PATH_IMAGE)
public interface ImageController {

    /**
     * This is the controller to insert an image in database.
     *
     * @param file
     * @return
     */
    @PostMapping(value = Constants.PATH_IMAGE_POST)
    @ApiOperation(code = 200, value = "It will insert the image", notes = "This will insert the given image")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Image has been inserted!."),
            @ApiResponse(code = 400, message = "The given Image is invalid."),
            @ApiResponse(code = 401, message = "This user is unauthorized."),
            @ApiResponse(code = 404, message = "This user was not found."),
    })
    ResponseEntity<UUID> saveImage(@RequestParam("imageFile") MultipartFile file);

    /**
     * This is the controller to get the image from database.
     *
     * @param imageUuid
     * @return
     */
    @GetMapping(value = Constants.PATH_IMAGE_GET)
    @ApiOperation(code = 200, value = "It will insert the image", notes = "This will insert the given image")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Image has been inserted!."),
            @ApiResponse(code = 400, message = "The given Image is invalid."),
            @ApiResponse(code = 401, message = "This user is unauthorized."),
            @ApiResponse(code = 404, message = "This user was not found."),
    })
    Image getImage(@PathVariable("imageUuid") String imageUuid);
}
