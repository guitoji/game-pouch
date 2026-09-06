package io.pouch.service;

import io.pouch.controller.dto.request.GameRequest;
import io.pouch.controller.dto.response.GameResponse;
import io.pouch.controller.dto.update.GameUpdate;
import io.pouch.entities.Game;
import io.pouch.entities.enums.Rating;
import io.pouch.exceptions.GameNotFoundException;
import io.pouch.repository.GameRepository;
import io.pouch.service.mapper.GameMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static io.pouch.repository.specs.GameSpecs.*;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public GameService(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    @Transactional
    public Game save(GameRequest request) {
        Game game = gameMapper.toEntity(request);
        return gameRepository.save(game);
    }

    @Transactional(readOnly = true)
    public Page<GameResponse> search(
            String title,
            String developer,
            String publisher,
            Rating rating,
            Integer page,
            Integer pageSize
    ) {
        Specification<Game> specs = ((root, query, cb) -> cb.conjunction());

        if (title != null) {
            specs = specs.and(titleLike(title));
        }

        if (developer != null) {
            specs = specs.and(developerLike(developer));
        }

        if (publisher != null) {
            specs = specs.and(publisherLike(publisher));
        }

        if (rating != null) {
            specs = specs.and(ratingEqual(rating));
        }

        Pageable pageRequest = PageRequest.of(page, pageSize);

        return gameRepository.findAll(specs, pageRequest).map(gameMapper::toResponse);
    }

    @Transactional
    public GameResponse update(String id, GameUpdate update) {
        Game game = gameRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new GameNotFoundException("Game not found."));

        gameMapper.update(update, game);
        return gameMapper.toResponse(gameRepository.save(game));
    }

    @Transactional
    public void delete(String id) {
        Game game = gameRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new GameNotFoundException(
                        "Method delete not possible, the game does not exist in the database."));

        gameRepository.delete(game);
    }

    @Transactional(readOnly = true)
    public GameResponse findById(String id) {
        Game game = gameRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new GameNotFoundException(
                        "Method delete not possible, the game does not exist in the database."));
        return gameMapper.toResponse(game);
    }

}
