package com.topzson.training.backend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.topzson.training.backend.business.TestBusiness;
import com.topzson.training.backend.exception.BaseException;
import com.topzson.training.backend.exception.FileException;
import com.topzson.training.backend.model.RegisterRequest;
import com.topzson.training.backend.model.TestResponse;

@RestController
@RequestMapping("/test")
public class TestApi {

    // etthod 1 fidld Injection
    // @Autowired
    // private TestBusiness business;

    // method 2 constrcutor Injection
    private final TestBusiness business;

    public TestApi(TestBusiness business) {
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
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) throws BaseException {
        // String response;
        // try {
        // response = business.register(request);
        // return ResponseEntity.ok(response);
        // } catch (Exception e) {
        // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        // }

        String response = business.register(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws FileException {
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);

    }

}
