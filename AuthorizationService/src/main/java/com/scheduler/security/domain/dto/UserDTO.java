package com.scheduler.security.domain.dto;

import com.scheduler.security.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String login;

    public static UserDTO from(User u) {
        return UserDTO.builder()
                .id(u.getId())
                .login(u.getLogin())
                .build();
    }
}
