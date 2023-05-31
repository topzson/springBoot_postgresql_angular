package com.topzson.training.backend.mapper;

import org.mapstruct.Mapper;

import com.topzson.training.backend.entity.User;
import com.topzson.training.backend.model.RegisterRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterRequest toRegisterResponse(User user);

}
