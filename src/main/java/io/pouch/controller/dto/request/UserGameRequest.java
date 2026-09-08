package io.pouch.controller.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserGameRequest(
        @NotNull(message = "this field is mandatory")
        UUID userId,
        @NotNull(message = "this field is mandatory")
        UUID gameId
) {
}
