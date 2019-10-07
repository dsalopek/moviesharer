package com.movieaccess.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MovieRestExceptionHandler {
    @ExceptionHandler(value = {MovieNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MovieErrorResponse unknownException(Exception exc) {
        return new MovieErrorResponse(HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MovieErrorResponse badRequest(Exception exc) {
        return new MovieErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());
    }
}