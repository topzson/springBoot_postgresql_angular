package com.topzson.training.backend.config.token;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.topzson.training.backend.services.TokenService;

public class TokenFilterConfiguerer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenService services;

    public TokenFilterConfiguerer(TokenService services) {
        this.services = services;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        TokenFilter filter = new TokenFilter(services);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

    }

}
