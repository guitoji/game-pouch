package io.pouch.repository.specs;

import io.pouch.entities.Game;
import io.pouch.entities.enums.Rating;
import org.springframework.data.jpa.domain.Specification;

public class GameSpecs {

    /*
        I also wanted to create a specification for Categories that would serve as a filter for game searches.
        However, I ran into a problem due to how the categories are stored in the database.
        Our entity has a list of categories stored as `varchar[]`.
     */

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
