package com.scheduler.security.service;

import com.scheduler.security.entity.User;
import com.scheduler.security.entity.dto.UserCredentialsDTO;
import com.scheduler.security.entity.dto.UserDTO;
import com.scheduler.security.exception.UserAlreadyExistsException;
import com.scheduler.security.repository.UserRepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepositoryCRUD userRepository;

    @Autowired
    public UserService(UserRepositoryCRUD userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO addNewUser(UserCredentialsDTO userDTO){
        this.userRepository.getByLogin(userDTO.getLogin()).ifPresent(u -> {
            throw new UserAlreadyExistsException(new UserDTO(u));
        });
        return new UserDTO(this.userRepository.save(new User(userDTO)));
    }
}
