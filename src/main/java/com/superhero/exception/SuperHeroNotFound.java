package com.superhero.exception;

public class SuperHeroNotFound extends RuntimeException {
    public SuperHeroNotFound(String message) {
        super(message);
    }
}
