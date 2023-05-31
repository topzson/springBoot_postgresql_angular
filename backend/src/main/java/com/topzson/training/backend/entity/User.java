package com.topzson.training.backend.entity;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

@Entity(name = "m_user")

public class User {
    @Id
    private String id;

}