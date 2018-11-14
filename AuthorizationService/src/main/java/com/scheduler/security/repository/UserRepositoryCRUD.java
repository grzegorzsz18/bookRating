package com.scheduler.security.repository;

import com.scheduler.security.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryCRUD extends CrudRepository<User, Long> {
    Optional<User> getByLogin(String login);

}
