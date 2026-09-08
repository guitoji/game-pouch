package io.pouch.service;

import io.pouch.repository.UserGameRepository;
import org.springframework.stereotype.Service;

@Service
public class UserGameService {

    private final UserGameRepository userGameRepository;

    public UserGameService(UserGameRepository userGameRepository) {
        this.userGameRepository = userGameRepository;
    }
}
