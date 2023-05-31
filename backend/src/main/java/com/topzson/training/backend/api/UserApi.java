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
import com.topzson.training.backend.entity.User;
import com.topzson.training.backend.exception.BaseException;
import com.topzson.training.backend.exception.FileException;
import com.topzson.training.backend.model.RegisterRequest;
import com.topzson.training.backend.model.TestResponse;

@RestController
@RequestMapping("/user")
public class UserApi {

    // etthod 1 fidld Injection
    // @Autowired
    // private TestBusiness business;

    // method 2 constrcutor Injection
    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @GetMapping
    public TestResponse test() {
        TestResponse response = new TestResponse();

        response.setName("Top");
        response.setFood("KFC");
        return response;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) throws BaseException {

        User response = business.register(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws FileException {
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);

    }

}
