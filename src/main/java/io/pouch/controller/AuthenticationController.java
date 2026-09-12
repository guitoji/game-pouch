package io.pouch.controller;

import io.pouch.controller.dto.request.LoginRequest;
import io.pouch.controller.dto.response.TokenResponse;
import io.pouch.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
