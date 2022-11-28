package com.project.noonee.config.exception;

public class CustomInternalServerErrorException extends RuntimeException {

    public CustomInternalServerErrorException(String message) {
        super(message);
    }
}