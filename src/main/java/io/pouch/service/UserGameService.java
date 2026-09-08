package io.pouch.service;

import io.pouch.repository.UserGameRepository;
import io.pouch.service.mapper.UserGameMapper;
import org.springframework.stereotype.Service;

@Service
public class UserGameService {

    private final UserGameRepository userGameRepository;
    private final UserGameMapper userGameMapper;

    public UserGameService(UserGameRepository userGameRepository, UserGameMapper userGameMapper) {
        this.userGameRepository = userGameRepository;
        this.userGameMapper = userGameMapper;
    }
}
