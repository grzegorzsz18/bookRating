package com.scheduler.security.controllers;

import com.scheduler.security.domain.dto.UserCredentialsDTO;
import com.scheduler.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PutMapping
    public ResponseEntity addNewUser(@RequestBody UserCredentialsDTO userCredentials) {
        return new ResponseEntity<>(this.userServiceImpl.addNewUser(userCredentials), HttpStatus.CREATED);
    }

}
