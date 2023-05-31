package com.topzson.training.backend.services;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.topzson.training.backend.entity.User;
import com.topzson.training.backend.exception.BaseException;
import com.topzson.training.backend.exception.UserException;
import com.topzson.training.backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(String email, String password, String name) throws BaseException {

        // validate
        if (Objects.isNull(email)) {
            throw UserException.createEmailNull();
        }
        if (Objects.isNull(password)) {
            // throw error
            throw UserException.createPasswordNull();
        }
        if (Objects.isNull(name)) {
            // throw error
            throw UserException.createNameNull();
        }

        // verify
        if (repository.existsByEmail(email)) {
            throw UserException.createEmailDuplicated();
        }

        // save
        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);
        return repository.save(entity);

    }
}
