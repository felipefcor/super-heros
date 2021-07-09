package com.superhero.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Super Hero not found")
    @ExceptionHandler(SuperHeroNotFound.class)
    public void handleSuperHeroNotFound(SuperHeroNotFound exception) {
    }
}