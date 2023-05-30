package com.topzson.training.backend.business;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.topzson.training.backend.exception.BaseException;
import com.topzson.training.backend.exception.ProductException;

@Service
public class ProductBusiness {

    public String getProductById(String id) throws BaseException {

        // Getdata from database
        if (Objects.equals("1234", id)) {
            throw ProductException.notFound();
        }
        return id;
    }
}