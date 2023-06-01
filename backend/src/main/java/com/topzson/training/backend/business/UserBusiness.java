package com.topzson.training.backend.business;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.topzson.training.backend.entity.User;
import com.topzson.training.backend.exception.BaseException;
import com.topzson.training.backend.exception.FileException;
import com.topzson.training.backend.exception.UserException;
import com.topzson.training.backend.mapper.UserMapper;
import com.topzson.training.backend.model.LoginRequest;
import com.topzson.training.backend.model.RegisterRequest;
import com.topzson.training.backend.services.UserService;

@Service
public class UserBusiness {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public String login(LoginRequest request) throws BaseException {
        // validate

        // verify database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if (opt.isEmpty()) {
            throw UserException.loginFailEmailNotFound();
        }
        User user = opt.get();
        if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
            throw UserException.loginFailPasswordIncorrect();

        }

        String token = "JWT to do";

        return token;

    }

    public RegisterRequest register(RegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());
        return userMapper.toRegisterResponse(user);
    }

    public String uploadProfilePicture(MultipartFile file) throws FileException {
        // validate file
        if (file == null) {
            throw FileException.fileNull();
        }

        // validate size
        if (file.getSize() > 1048576 * 2) {
            throw FileException.fileMaxSize();
        }
        String contentType = file.getContentType();
        if (contentType == null) {

        }
        List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
        if (supportTypes.contains(supportTypes)) {
            throw FileException.unsupported();
        }

        try {
            byte[] bytes = file.getBytes();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentType;
    }

}
