package io.pouch.exceptions;

public class UserGameNotFoundException extends RuntimeException {
    public UserGameNotFoundException(String message) {
        super(message);
    }
}
