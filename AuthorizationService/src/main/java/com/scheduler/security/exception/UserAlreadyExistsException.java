package com.scheduler.security.exception;

import com.scheduler.security.domain.dto.UserDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(UserDTO user) {
        super("User " + user.getLogin() + " already exists");
    }
}
