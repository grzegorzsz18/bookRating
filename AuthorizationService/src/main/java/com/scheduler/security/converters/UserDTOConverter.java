package com.scheduler.security.converters;

import com.scheduler.security.domain.User;
import com.scheduler.security.domain.dto.UserDTO;

public class UserDTOConverter {

    private UserDTOConverter() {
    }

    public static UserDTO userToUserDTO(User user){
        return UserDTO.builder()
                .login(user.getLogin())
                .build();
    }
}
