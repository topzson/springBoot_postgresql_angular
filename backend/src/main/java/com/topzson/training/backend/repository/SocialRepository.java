package com.topzson.training.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.topzson.training.backend.entity.Social;
import com.topzson.training.backend.entity.User;

public interface SocialRepository extends CrudRepository<Social, String> {
    Optional<Social> findByUser(User user);

}
