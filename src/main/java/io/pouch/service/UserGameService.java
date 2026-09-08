package io.pouch.service;

import io.pouch.controller.dto.request.UserGameRequest;
import io.pouch.entities.Game;
import io.pouch.entities.User;
import io.pouch.entities.UserGame;
import io.pouch.exceptions.GameNotFoundException;
import io.pouch.exceptions.UserNotFoundException;
import io.pouch.repository.GameRepository;
import io.pouch.repository.UserRepository;
import io.pouch.repository.UserGameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserGameService {

    private final UserGameRepository usergameRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public UserGameService(UserGameRepository usergameRepository, UserRepository userRepository, GameRepository gameRepository) {
        this.usergameRepository = usergameRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional
    public UserGame save(UserGameRequest request) {
        User user  = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found in database."));

        Game game = gameRepository.findById(request.gameId())
                .orElseThrow(() -> new GameNotFoundException("Game not found in database."));

        UserGame usergame = new UserGame();
        usergame.setUser(user);
        usergame.setGame(game);

        return usergameRepository.save(usergame);
    }
}
