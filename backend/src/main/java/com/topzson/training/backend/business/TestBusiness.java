package com.topzson.training.backend.business;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.topzson.training.backend.exception.BaseException;
import com.topzson.training.backend.exception.UserException;
import com.topzson.training.backend.model.RegisterRequest;

@Service
public class TestBusiness {
    public String register(RegisterRequest request) throws BaseException {
        if (request == null) {
            throw UserException.requestNull();
        }
        if (Objects.isNull(request.getEmail())) {
            throw UserException.emailNull();
        }
        return "";
    }

}
