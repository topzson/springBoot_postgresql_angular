package com.topzson.training.backend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.topzson.training.backend.business.UserBusiness;
import com.topzson.training.backend.exception.BaseException;
import com.topzson.training.backend.exception.FileException;
import com.topzson.training.backend.model.LoginRequest;
import com.topzson.training.backend.model.RegisterRequest;
import com.topzson.training.backend.model.RegisterResponse;

@RestController
@RequestMapping("/user")
public class UserApi {

    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws BaseException {
        String response = business.login(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public RegisterResponse test() {
        RegisterResponse response = new RegisterResponse();

        return response;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<RegisterRequest> register(@RequestBody RegisterRequest request) throws BaseException {

        RegisterRequest response = business.register(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws FileException {
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);

    }

}
