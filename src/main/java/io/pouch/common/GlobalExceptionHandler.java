package io.pouch.common;

import io.pouch.controller.dto.error.ErrorField;
import io.pouch.controller.dto.error.ErrorNotice;
import io.pouch.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorNotice handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorField> errorFields = fieldErrors
                .stream()
                .map(fieldError -> new ErrorField(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return new ErrorNotice(HttpStatus.BAD_REQUEST.value(), "Invalid argument provided in the request body.", errorFields);
    }

    @ExceptionHandler(DeleteReleasedGameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorNotice handleDeleteReleasedGameException(DeleteReleasedGameException e) {
        return new ErrorNotice(HttpStatus.BAD_REQUEST.value(), e.getMessage(), List.of());
    }

    @ExceptionHandler(DuplicateGameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorNotice handleDuplicateGameException(DuplicateGameException e) {
        return new ErrorNotice(HttpStatus.CONFLICT.value(), e.getMessage(), List.of());
    }

    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorNotice handleGameNotFoundException(GameNotFoundException e) {
        return new ErrorNotice(HttpStatus.NOT_FOUND.value(), e.getMessage(), List.of());
    }

    @ExceptionHandler(UserGameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorNotice handlerUserGameNotFoundException(UserGameNotFoundException e) {
        return new ErrorNotice(HttpStatus.NOT_FOUND.value(), e.getMessage(), List.of());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorNotice handlerUserNotFoundException(UserNotFoundException e) {
        return new ErrorNotice(HttpStatus.NOT_FOUND.value(), e.getMessage(), List.of());
    }
}
