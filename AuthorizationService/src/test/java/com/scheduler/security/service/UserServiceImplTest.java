package com.scheduler.security.service;

import com.scheduler.security.domain.User;
import com.scheduler.security.domain.dto.UserCredentialsDTO;
import com.scheduler.security.exception.UserWrongCredentialsException;
import com.scheduler.security.repository.UserRepositoryCRUD;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepositoryCRUD userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void shouldGetUser() {
        final String password = "password";
        final String login = "login";
        final long id = 1;

        User user = User.builder()
                .id(id)
                .login(login)
                .password(password)
                .build();
        UserCredentialsDTO userCredentialsDTO = UserCredentialsDTO.builder()
                .login(login)
                .password(password)
                .build();

        when(userRepository.getByLogin(login)).thenReturn(Optional.of(user));

        assertThat(userService.getUser(userCredentialsDTO)).isEqualToComparingFieldByField(user);
    }

    @Test(expected = UserWrongCredentialsException.class)
    public void shouldThrowError() {
        final String correctPassword = "password";
        final String wrongPassword = "wrongPassword";
        final String login = "login";
        final long id = 1;

        User user = User.builder()
                .id(id)
                .login(login)
                .password(correctPassword)
                .build();
        UserCredentialsDTO userCredentialsDTO = UserCredentialsDTO.builder()
                .login(login)
                .password(wrongPassword)
                .build();

        when(userRepository.getByLogin(login)).thenReturn(Optional.of(user));

        userService.getUser(userCredentialsDTO);
    }

}