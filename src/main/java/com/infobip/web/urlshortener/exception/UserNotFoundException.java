package com.infobip.web.urlshortener.exception;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2342964347756571583L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
