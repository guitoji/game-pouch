package io.pouch.service;

import io.pouch.controller.dto.request.GameRequest;
import io.pouch.entities.Game;
import io.pouch.repository.GameRepository;
import io.pouch.service.mapper.GameMapper;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public GameService(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    public Game save(GameRequest request) {
        Game game = gameMapper.toEntity(request);
        return gameRepository.save(game);
    }
}
