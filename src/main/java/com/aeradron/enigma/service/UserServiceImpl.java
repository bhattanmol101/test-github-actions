package com.aeradron.enigma.service;

import com.aeradron.enigma.datasource.entity.Address;
import com.aeradron.enigma.datasource.entity.Interests;
import com.aeradron.enigma.datasource.entity.Skills;
import com.aeradron.enigma.datasource.entity.User;
import com.aeradron.enigma.model.AddressRequest;
import com.aeradron.enigma.model.UserRequest;
import com.aeradron.enigma.service.dao.UserDAO;
import com.aeradron.enigma.service.utility.UtilityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void saveUser(UserRequest userRequest) throws Exception {
        userDAO.saveUser(convertUserRequestToUserEntity(userRequest,true));
    }

    @Override
    public void updateUser(UserRequest userRequest) throws Exception {
        userDAO.saveUser(convertUserRequestToUserEntity(userRequest,false));
    }

    @Override
    public UserRequest getUser(UUID uuid) throws Exception {
        return convertUserEntityToRequest(userDAO.getUserByUUID(uuid));
    }

    @Override
    public int deleteUser(UUID uuid) {
        return userDAO.deleteUserByUUID(uuid);
    }

    private UserRequest convertUserEntityToRequest(User user) throws IOException {
        if (user != null) {
            UserRequest userRequest = new UserRequest();
            userRequest.setAbout(user.getAbout());
            ObjectMapper mapper = new ObjectMapper();
            userRequest.setDateOfBirth(user.getDateOfBirth());
            userRequest.setEmail(user.getEmail());
            userRequest.setFirstName(user.getFirstName());
            userRequest.setLastName(user.getLastName());
            userRequest.setGender(user.getGender());
            userRequest.setPhoneNumber(user.getPhoneNumber());
            userRequest.setProfession(user.getProfession());
            userRequest.setUserType(user.getUserType());
            userRequest.setWorkExperience(user.getWorkExperience());
            userRequest.setCompany(user.getCompany());
            userRequest.setDesignation(user.getDesignation());
            user.setUuid(user.getUuid());
            return userRequest;
        } else {
            return null;
        }
    }

    private User convertUserRequestToUserEntity(UserRequest userRequest, boolean save)
            throws IOException {
        if (userRequest != null) {
            User user = new User();
            if (save) {
                user.setUuid(UUID.randomUUID());
            } else {
                user.setUuid(UUID.fromString(userRequest.getUuid()));
            }
            user.setAbout(userRequest.getAbout());
            ObjectMapper mapper = new ObjectMapper();
            String addressJson = mapper.writeValueAsString(userRequest.getAddress());
            user.setDateOfBirth(userRequest.getDateOfBirth());
            user.setEmail(userRequest.getEmail());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setGender(userRequest.getGender());
            user.setPassword(UtilityService.passwordEncoder(userRequest.getPassword()));
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setProfession(userRequest.getProfession());
            user.setUserType(userRequest.getUserType());
            user.setWorkExperience(userRequest.getWorkExperience());
            user.setCompany(userRequest.getCompany());
            user.setImage(UUID.fromString(userRequest.getImageKey()));
            user.setDesignation(userRequest.getDesignation());
            return user;
        } else {
            return null;
        }
    }
}
