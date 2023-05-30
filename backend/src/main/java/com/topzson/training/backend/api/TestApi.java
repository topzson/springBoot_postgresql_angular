package com.topzson.training.backend.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topzson.training.backend.model.RegisterRequest;
import com.topzson.training.backend.model.TestResponse;

@RestController
@RequestMapping("/test")
public class TestApi {

    @GetMapping
    public TestResponse test() {
        TestResponse response = new TestResponse();

        response.setName("Top");
        response.setFood("KFC");
        return response;
    }

    @PostMapping
    @RequestMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        return "Recived " + request;
    }

}
