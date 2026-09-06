package io.pouch.controller.dto.update;

import io.pouch.entities.enums.Category;
import io.pouch.entities.enums.Rating;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record GameUpdate(
        String title,
        String description,
        List<Category> categories,
        String developer,
        String publisher,
        LocalDate releasedIn,
        Rating rating,
        @Positive
        BigDecimal price
) {
}
