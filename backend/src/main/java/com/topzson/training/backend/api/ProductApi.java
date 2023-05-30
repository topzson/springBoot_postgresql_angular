package com.topzson.training.backend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topzson.training.backend.business.ProductBusiness;
import com.topzson.training.backend.exception.BaseException;

@RestController
@RequestMapping("/product")

public class ProductApi {

    private final ProductBusiness business;

    public ProductApi(ProductBusiness business) {
        this.business = business;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String id) throws BaseException {
        String response = business.getProductById(id);
        return ResponseEntity.ok(response);
    }
}