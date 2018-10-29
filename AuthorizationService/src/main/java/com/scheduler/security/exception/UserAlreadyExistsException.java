package com.scheduler.security.exception;

import com.scheduler.security.entity.dto.UserDTO;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(UserDTO user) {

    }
}
