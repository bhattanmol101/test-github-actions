package com.aeradron.enigma.service;

import com.aeradron.enigma.model.UserRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.UUID;

public interface UserService {

    void saveUser(UserRequest user) throws Exception;

    void updateUser(UserRequest userRequest) throws Exception;

    UserRequest getUser(UUID uuid) throws Exception;

    int deleteUser(UUID uuid);

}
