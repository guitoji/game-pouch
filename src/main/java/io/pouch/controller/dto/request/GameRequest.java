package io.pouch.controller.dto.request;

import io.pouch.entities.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record GameRequest(
        @NotBlank(message = "this field is mandatory")
        String title,
        @NotBlank(message = "this field is mandatory")
        String description,
        List<Category> categories,
        @NotBlank(message = "this field is mandatory")
        String developer,
        @NotBlank(message = "this field is mandatory")
        String publisher,
        @Positive
        BigDecimal price
) {
}
