package io.pouch.service;

import io.pouch.controller.dto.request.GameRequest;
import io.pouch.controller.dto.response.GameResponse;
import io.pouch.entities.Game;
import io.pouch.entities.enums.Category;
import io.pouch.entities.enums.Rating;
import io.pouch.repository.GameRepository;
import io.pouch.service.mapper.GameMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
