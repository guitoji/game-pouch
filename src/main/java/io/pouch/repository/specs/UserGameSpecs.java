package io.pouch.repository.specs;

import io.pouch.entities.Game;
import io.pouch.entities.UserGame;
import io.pouch.entities.enums.Rating;
import io.pouch.entities.enums.Status;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class UserGameSpecs {

    public static Specification<UserGame> gameTitleLike(String title) {
        return (root, query, cb) -> {
            Join<UserGame, Game> joinGame = root.join("game", JoinType.LEFT);
            return cb.like(cb.upper(joinGame.get("title")), "%" + title.toUpperCase() + "%");
        };
    }

    public static Specification<UserGame> statusEqual(Status status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<UserGame> ratingEqual(Rating rating) {
        return (root, query, cb) -> cb.equal(root.get("rating"), rating);
    }
}
