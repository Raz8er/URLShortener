package com.infobip.web.urlshortener.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -8162324010082886923L;

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
