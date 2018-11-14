package com.scheduler.security.service;

import com.scheduler.security.converters.UserConverter;
import com.scheduler.security.converters.UserDTOConverter;
import com.scheduler.security.domain.User;
import com.scheduler.security.domain.dto.UserCredentialsDTO;
import com.scheduler.security.domain.dto.UserDTO;
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

    public UserDTO addNewUser(UserCredentialsDTO userCredentialsDTO){
        this.userRepository.getByLogin(userCredentialsDTO.getLogin()).ifPresent((User u) -> {
            throw new UserAlreadyExistsException(UserDTOConverter.userToUserDTO(u));
        });
        return UserDTOConverter.userToUserDTO(
                this.userRepository.save(UserConverter.userCredientialsDTOtoUser(userCredentialsDTO)));
    }
}
