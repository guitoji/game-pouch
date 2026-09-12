package io.pouch.controller;

import io.pouch.service.AuthenticationService;
import io.pouch.controller.dto.response.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(Authentication authentication) {
        return ResponseEntity.ok(authenticationService.authenticate(authentication));
    }
}
