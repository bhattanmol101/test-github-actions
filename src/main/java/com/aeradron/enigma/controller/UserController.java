package com.aeradron.enigma.controller;

import com.aeradron.enigma.model.UserRequest;
import com.aeradron.enigma.service.utility.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"User-API"})
@RequestMapping(Constants.PATH_USER)
public interface UserController {

    /**
     * This is the controller for getting the users.
     *
     * @param userUuid
     * @return
     */
    @GetMapping(value = Constants.PATH_USER_GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(code = 200, value = "It will return the user", notes = "This will retrieve the given user")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "The given UUID is invalid."),
            @ApiResponse(code = 401, message = "This user is unauthorized."),
            @ApiResponse(code = 404, message = "This user was not found."),
    })
    ResponseEntity<UserRequest> getUser(@PathVariable(value = "userUuid") String userUuid) throws Exception;

    /**
     * This is the controller to insert a user in database.
     *
     * @param userRequest
     * @return
     */
    @PostMapping(value = Constants.PATH_USER_POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(code = 200, value = "It will insert the user", notes = "This will insert the given user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User has been created!."),
            @ApiResponse(code = 400, message = "The given user is invalid."),
            @ApiResponse(code = 401, message = "This user is unauthorized."),
            @ApiResponse(code = 404, message = "This user was not found."),
    })
    ResponseEntity<String> saveUser(@RequestBody UserRequest userRequest);

    /**
     * This is the controller to delete a user from database.
     *
     * @param userUuid
     * @return
     */
    @DeleteMapping(value = Constants.PATH_USER_DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(code = 200, value = "It will delete the user", notes = "This will delete the given user")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "The given UUID is invalid."),
            @ApiResponse(code = 401, message = "This user is unauthorized."),
            @ApiResponse(code = 404, message = "This user was not found."),
    })
    ResponseEntity<String> deleteUser(@PathVariable(value = "userUuid") String userUuid);
}