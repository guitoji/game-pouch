package io.pouch.repository.specs;

import io.pouch.entities.Game;
import io.pouch.entities.enums.Rating;
import org.springframework.data.jpa.domain.Specification;

public class GameSpecs {

    public static Specification<Game> titleLike(String title) {
        return ((root, query, cb) -> cb.like(cb.upper(root.get("title")), "%" + title.toUpperCase() + "%"));
    }

    public static Specification<Game> developerLike(String developer) {
        return ((root, query, cb) -> cb.like(cb.upper(root.get("developer")), "%" + developer.toUpperCase() + "%"));
    }

    public static Specification<Game> publisherLike(String publisher) {
        return ((root, query, cb) -> cb.like(cb.upper(root.get("publisher")), "%" + publisher.toUpperCase() + "%"));
    }

    public static Specification<Game> ratingEqual(Rating rating) {
        return ((root, query, cb) -> cb.equal(root.get("rating"), rating));
    }
}
