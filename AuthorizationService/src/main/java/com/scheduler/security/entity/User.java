package com.scheduler.security.entity;

import com.scheduler.security.entity.dto.UserCredentialsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String login;
    private String password;


    public User(UserCredentialsDTO userCredentials) {
        this.login = userCredentials.getLogin();
        this.password = userCredentials.getPassword();

    }
}
