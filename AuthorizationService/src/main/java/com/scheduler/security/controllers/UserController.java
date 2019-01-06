package com.scheduler.security.controllers;

import com.scheduler.security.domain.User;
import com.scheduler.security.domain.dto.UserCredentialsDTO;
import com.scheduler.security.domain.dto.UserDTO;
import com.scheduler.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity getUser(@RequestBody UserCredentialsDTO userCredentials) {
        return new ResponseEntity<>(this.userServiceImpl.getUser(userCredentials), HttpStatus.OK);
    }

    @GetMapping(path = "/id/{login}")
    public ResponseEntity getIdByLogin(@PathVariable String login) {
        return new ResponseEntity<>(this.userServiceImpl.getIdByLogin(login), HttpStatus.OK);
    }

}
