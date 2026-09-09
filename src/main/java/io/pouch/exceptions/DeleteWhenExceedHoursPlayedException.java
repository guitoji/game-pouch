package io.pouch.exceptions;

public class DeleteWhenExceedHoursPlayedException extends RuntimeException {
    public DeleteWhenExceedHoursPlayedException(String message) {
        super(message);
    }
}
