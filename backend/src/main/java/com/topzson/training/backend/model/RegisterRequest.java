package com.topzson.training.backend.model;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String name;

}