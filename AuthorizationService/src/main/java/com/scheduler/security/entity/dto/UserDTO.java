package com.scheduler.security.entity.dto;

import com.scheduler.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {

    private String login;

    public UserDTO(User user) {
        this.login = user.getLogin();
    }
}
