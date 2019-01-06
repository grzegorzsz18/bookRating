package com.scheduler.security.domain;

import com.scheduler.security.domain.dto.UserCredentialsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String login;
    private String password;

    public static User from(UserCredentialsDTO userCredentialsDTO) {
        return User.builder()
                .login(userCredentialsDTO.getLogin())
                .password(userCredentialsDTO.getPassword())
                .build();
    }
}
