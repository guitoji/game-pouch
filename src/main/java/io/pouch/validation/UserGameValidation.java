package io.pouch.validation;

import io.pouch.controller.dto.update.UserGameUpdate;
import io.pouch.entities.Game;
import io.pouch.entities.User;
import io.pouch.entities.UserGame;
import io.pouch.entities.enums.Status;
import io.pouch.exceptions.DeleteWhenExceedHoursPlayedException;
import io.pouch.exceptions.DuplicateUserGameException;
import io.pouch.exceptions.WishingStatusNonAvailableException;
import io.pouch.repository.UserGameRepository;
import org.springframework.stereotype.Component;

@Component
public class UserGameValidation {

    private final Double LIMIT_HOURS_PLAYED = 5.0;

    private UserGameRepository userGameRepository;

    public UserGameValidation(UserGameRepository userGameRepository) {
        this.userGameRepository = userGameRepository;
    }

    public void validateSave(Game game, User user) {
        if (userGameAlreadyExists(game, user)) {
            throw new DuplicateUserGameException("This User already has this UserGame");
        }
    }

    public void validateUpdate(UserGameUpdate update) {
        if (updateStatusIsWishing(update)) {
            throw new WishingStatusNonAvailableException("Status 'WISHING' not available, you already bought this game");
        }
    }

    //@validateDelete method will be refactored later, the condition exceedTheHoursPlayed will be implemented to refund ;)
    public void validateDelete(UserGame userGame) {
        if (exceedTheHoursPlayed(userGame)) {
            throw new DeleteWhenExceedHoursPlayedException(
                    "Method delete not possible, the User exceeded the limit of hours played allowed. " +
                            "Please, contact the administration.");
        }
    }

    private boolean userGameAlreadyExists(Game game, User user) {
        return userGameRepository.existsByGameAndUser(game, user);
    }

    private boolean updateStatusIsWishing(UserGameUpdate update) {
        return update.status() != null && update.status().equals(Status.WISHING);
    }

    private boolean exceedTheHoursPlayed(UserGame userGame) {
        return userGame.getHoursPlayed() > LIMIT_HOURS_PLAYED;
    }
}
