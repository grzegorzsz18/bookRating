package com.scheduler.security.service;

import com.scheduler.security.domain.dto.UserCredentialsDTO;
import com.scheduler.security.domain.dto.UserDTO;

public interface UserService {

    UserDTO addNewUser(UserCredentialsDTO user);
}
