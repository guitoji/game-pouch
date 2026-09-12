package io.pouch.controller.dto.request;

import jakarta.validation.constraints.Email;

public record UserRequest(
        String username,
        @Email
        String email,
        String password
) {
}
