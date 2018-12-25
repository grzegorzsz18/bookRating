package com.scheduler.security.domain.dto;

import com.scheduler.security.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCredentialsDTO {
    private String login;
    private String password;

    public static UserCredentialsDTO from(User user) {
        return UserCredentialsDTO.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
