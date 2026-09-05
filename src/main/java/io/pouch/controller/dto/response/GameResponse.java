package io.pouch.controller.dto.response;

import io.pouch.entities.enums.Category;
import io.pouch.entities.enums.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record GameResponse(
        String title,
        String description,
        List<Category> categories,
        String developer,
        String publisher,
        LocalDate releasedIn,
        Rating rating,
        BigDecimal price
) {
}
