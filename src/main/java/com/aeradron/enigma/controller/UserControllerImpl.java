package com.aeradron.enigma.controller;

import com.aeradron.enigma.model.UserRequest;
import com.aeradron.enigma.service.UserService;
import com.aeradron.enigma.service.exceptions.InternalException;
import com.aeradron.enigma.service.exceptions.MandatoryFieldMissingException;
import com.aeradron.enigma.service.utility.UtilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.UUID;

@Controller
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * This is the controller for getting the users.
     *
     * @param userUuid
     * @return
     */
    @Override
    public ResponseEntity<UserRequest> getUser(String userUuid) throws Exception {
        if (UtilityService.checkStringNotNull(userUuid)) {
            try {
                UserRequest user = userService.getUser(UUID.fromString(userUuid));
                return ResponseEntity.ok(user);
            } catch (Exception e) {
                logger.error("Error occurred while fetching the user with error: " + e, e);
                throw new InternalException(e.getMessage());
            }
        } else {
            throw new MandatoryFieldMissingException("User UUID is missing!!");
        }
    }

    /**
     * This is the controller to insert a user in database.
     *
     * @param userRequest
     * @return
     */
    @Override
    public ResponseEntity<String> saveUser(UserRequest userRequest) {
        if (userRequest != null) {
            try {
                if (userRequest.getUuid() != null) {
                    if (userService.getUser(UUID.fromString(userRequest.getUuid())) != null) {
                        userService.updateUser(userRequest);
                        return ResponseEntity.ok("User has been updated!");
                    } else {
                        throw new InternalException("Could not find the User with the given UUID.");
                    }
                } else {
                    userService.saveUser(userRequest);
                    return ResponseEntity.ok("User has been created!");
                }
            } catch (Exception e) {
                logger.error("Error occurred while saving the user with error: " + e, e);
                throw new InternalException(e.getMessage());
            }
        } else {
            throw new MandatoryFieldMissingException("User Details are missing!");
        }
    }

    /**
     * This is the controller to delete a user from database.
     *
     * @param userUuid
     * @return
     */
    @Override
    public ResponseEntity<String> deleteUser(String userUuid) {
        return null;
    }
}
