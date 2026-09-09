package io.pouch.service;

import io.pouch.controller.dto.request.UserGameRequest;
import io.pouch.controller.dto.response.UserGameResponse;
import io.pouch.controller.dto.update.UserGameUpdate;
import io.pouch.entities.Game;
import io.pouch.entities.User;
import io.pouch.entities.UserGame;
import io.pouch.entities.enums.Rating;
import io.pouch.entities.enums.Status;
import io.pouch.exceptions.GameNotFoundException;
import io.pouch.exceptions.UserGameNotFoundException;
import io.pouch.exceptions.UserNotFoundException;
import io.pouch.repository.GameRepository;
import io.pouch.repository.UserGameRepository;
import io.pouch.repository.UserRepository;
import io.pouch.service.mapper.UserGameMapper;
import io.pouch.validation.UserGameValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static io.pouch.repository.specs.UserGameSpecs.*;

@Service
public class UserGameService {

    private final UserGameRepository userGameRepository;
    private final UserGameMapper userGameMapper;
    private final UserGameValidation userGameValidation;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public UserGameService(UserGameRepository userGameRepository, UserRepository userRepository, GameRepository gameRepository, UserGameMapper userGameMapper, UserGameValidation userGameValidation) {
        this.userGameRepository = userGameRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.userGameMapper = userGameMapper;
        this.userGameValidation = userGameValidation;
    }

    @Transactional
    public UserGame save(UserGameRequest request) {
        User user  = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found in the database."));

        Game game = gameRepository.findById(request.gameId())
                .orElseThrow(() -> new GameNotFoundException("Game not found in the database."));

        userGameValidation.validateSave(game, user);

        UserGame usergame = new UserGame();
        usergame.setUser(user);
        usergame.setGame(game);

        return userGameRepository.save(usergame);
    }

    @Transactional(readOnly = true)
    public Page<UserGameResponse> search(
            String title,
            Status status,
            Rating rating,
            Integer page,
            Integer pageSize
    ) {
        Specification<UserGame> specs = (root, query, cb) -> cb.conjunction();

        if (title != null) specs = specs.and(gameTitleLike(title));

        if (status != null) specs = specs.and(statusEqual(status));

        if (rating != null) specs = specs.and(ratingEqual(rating));

        Pageable pageRequest = PageRequest.of(page, pageSize);

        return userGameRepository.findAll(specs, pageRequest).map(userGameMapper::toResponse);
    }


    @Transactional
    public UserGameResponse update(String id, UserGameUpdate update) {
        userGameValidation.validateUpdate(update);

        UserGame userGame = userGameRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserGameNotFoundException("UserGame not found in the database"));

        userGameMapper.update(update, userGame);
        return userGameMapper.toResponse(userGameRepository.save(userGame));
    }

    @Transactional
    public void delete(String id) {
        UserGame userGame = userGameRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserGameNotFoundException(
                        "Method delete not possible, the UserGame does not exist in the database."));

        userGameValidation.validateDelete(userGame);
        userGameRepository.delete(userGame);
    }
}
