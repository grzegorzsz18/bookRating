package com.scheduler.security.controllers;

import com.scheduler.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping(produces = "application/json")
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping(path = "/id/{login}")
    public ResponseEntity getIdByLogin(@PathVariable String login) {
        return new ResponseEntity<>(this.userServiceImpl.getIdByLogin(login), HttpStatus.OK);
    }

}
