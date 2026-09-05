package io.pouch.controller.dto.response;

import io.pouch.entities.enums.Category;
import io.pouch.entities.enums.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GameResponse(
        String title,
        String description,
        Category category,
        String developer,
        String publisher,
        LocalDate releasedIn,
        Rating rating,
        BigDecimal price
) {
}
