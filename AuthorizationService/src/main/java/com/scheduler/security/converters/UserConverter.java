package com.scheduler.security.converters;

import com.scheduler.security.domain.User;
import com.scheduler.security.domain.dto.UserCredentialsDTO;
import com.scheduler.security.domain.dto.UserDTO;

public class UserConverter {

    private UserConverter() {

    }

    public static User userDTOtoUser(UserDTO userDTO) {
        return User.builder()
                .login(userDTO.getLogin())
                .build();
    }

    public static User userCredientialsDTOtoUser(UserCredentialsDTO userCredentialsDTO) {
        return User.builder()
                .login(userCredentialsDTO.getLogin())
                .password(userCredentialsDTO.getPassword())
                .build();
    }
}
