package com.topzson.training.backend.business;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.topzson.training.backend.exception.BaseException;
import com.topzson.training.backend.exception.FileException;
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
