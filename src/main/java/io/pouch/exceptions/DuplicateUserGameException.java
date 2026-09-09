package io.pouch.exceptions;

public class DuplicateUserGameException extends RuntimeException {
    public DuplicateUserGameException(String message) {
        super(message);
    }
}
