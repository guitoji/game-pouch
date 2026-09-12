package io.pouch.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;

    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public TokenResponse authenticate(Authentication authentication) {
        String token = jwtService.generateToken(authentication);
        return new TokenResponse(token);
    }
}
