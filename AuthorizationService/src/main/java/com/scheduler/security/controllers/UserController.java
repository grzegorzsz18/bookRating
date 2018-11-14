package com.scheduler.security.controllers;

import com.scheduler.security.domain.dto.UserCredentialsDTO;
import com.scheduler.security.service.UserService;
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

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity addNewUser(@RequestBody UserCredentialsDTO userCredentials) {
        return new ResponseEntity<>(this.userService.addNewUser(userCredentials), HttpStatus.CREATED);
    }

}
