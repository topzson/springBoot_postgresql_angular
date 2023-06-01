package com.topzson.training.backend.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.topzson.training.backend.entity.Social;
import com.topzson.training.backend.entity.User;
import com.topzson.training.backend.repository.SocialRepository;

@Service
public class SocialService {
    private final SocialRepository repository;

    public SocialService(SocialRepository repository) {
        this.repository = repository;
    }

    public Optional<Social> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Social create(User user, String facebook, String line, String instagram, String tiktok) {
        Social entity = new Social();

        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);
        entity.setTiktok(tiktok);

        return repository.save(entity);

    }

}
