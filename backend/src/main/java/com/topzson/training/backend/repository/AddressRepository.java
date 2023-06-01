package com.topzson.training.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.topzson.training.backend.entity.Address;
import com.topzson.training.backend.entity.User;

public interface AddressRepository extends CrudRepository<Address, String> {
    List<Address> findByUser(User user);

}
