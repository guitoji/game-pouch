package io.pouch.validation;

import io.pouch.controller.dto.request.GameRequest;
import io.pouch.controller.dto.update.GameUpdate;
import io.pouch.entities.Game;
import io.pouch.exceptions.DeleteReleasedGameException;
import io.pouch.exceptions.DuplicateGameException;
import io.pouch.repository.GameRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class GameValidation {

    private  final GameRepository gameRepository;

    public GameValidation(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void validateRequest(GameRequest request) {
        if (gameAlreadyExists(request)) {
            throw new DuplicateGameException("A game with this name already exists in the database.");
        }
    }

    public void validateUpdate(String id, GameUpdate update) {
        if (gameUpdateWithDuplicatedTitle(id, update)) {
            throw new DuplicateGameException("A game with this name already exists in the database.");
        }
    }

    public void validateDelete(Game game) {
        if (gameWasReleased(game)) {
            throw new DeleteReleasedGameException("It is not possible to delete this game, as it has already been released.");
        }
    }

    private boolean gameAlreadyExists(GameRequest request) {
        return gameRepository.findGameByTitleEqualsIgnoreCase(request.title()) != null;
    }

    private boolean gameUpdateWithDuplicatedTitle(String id, GameUpdate update) {
        if (update.title() == null) {
            return false;
        }

        Game gameFound = gameRepository.findGameByTitleEqualsIgnoreCase(update.title());

        return gameFound != null && !gameFound.getGameId().equals(UUID.fromString(id));
    }

    private boolean gameWasReleased(Game game) {
        return game.getReleasedIn() != null && game.getReleasedIn().isBefore(LocalDate.now());
    }
}
