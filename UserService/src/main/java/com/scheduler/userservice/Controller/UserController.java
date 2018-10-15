package com.scheduler.userservice.Controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public ResponseEntity<String> findByOrganization() {
        System.err.print("okkokokokokokokoko");
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }
}