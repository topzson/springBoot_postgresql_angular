package com.topzson.training.backend.business;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.topzson.training.backend.entity.User;
import com.topzson.training.backend.exception.BaseException;
import com.topzson.training.backend.exception.FileException;
import com.topzson.training.backend.model.RegisterRequest;
import com.topzson.training.backend.services.UserService;

@Service
public class UserBusiness {

    private final UserService userService;

    public UserBusiness(UserService userService) {
        this.userService = userService;
    }

    public User register(RegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());
        // TODO: mapper
        return user;
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
