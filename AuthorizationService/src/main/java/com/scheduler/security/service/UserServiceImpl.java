package com.scheduler.security.service;

import com.scheduler.security.domain.User;
import com.scheduler.security.domain.dto.UserCredentialsDTO;
import com.scheduler.security.domain.dto.UserDTO;
import com.scheduler.security.exception.UserAlreadyExistsException;
import com.scheduler.security.exception.UserWrongCredentialsException;
import com.scheduler.security.repository.UserRepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryCRUD userRepository;

    @Autowired
    public UserServiceImpl(UserRepositoryCRUD userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO addNewUser(UserCredentialsDTO userCredentialsDTO) {
        this.userRepository.getByLogin(userCredentialsDTO.getLogin()).ifPresent((User u) -> {
            throw new UserAlreadyExistsException(UserDTO.from(u));
        });
        return UserDTO.from(this.userRepository.save(User.from(userCredentialsDTO)));
    }

    public UserDTO getUser(UserCredentialsDTO userCredentials) {
        return UserDTO.from(this.userRepository.getByLogin(userCredentials.getLogin())
                .filter(user -> user.getPassword().equals(userCredentials.getPassword()))
                .orElseThrow(UserWrongCredentialsException::new));

    }
}
