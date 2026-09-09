package io.pouch.controller.dto.update;

import io.pouch.entities.enums.Rating;
import io.pouch.entities.enums.Status;

public record UserGameUpdate(
        Status status,
        Rating rating,
        String review
) {
}
