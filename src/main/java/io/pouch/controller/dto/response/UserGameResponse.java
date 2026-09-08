package io.pouch.controller.dto.response;

import io.pouch.entities.enums.Rating;
import io.pouch.entities.enums.Status;

public record UserGameResponse(
        GameResponse gameResponse,
        Status status,
        Rating rating,
        Double hoursPlayed,
        String review
) {
}
