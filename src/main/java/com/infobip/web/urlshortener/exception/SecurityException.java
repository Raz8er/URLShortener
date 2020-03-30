package com.infobip.web.urlshortener.exception;

public class SecurityException extends RuntimeException {
    private static final long serialVersionUID = -8667583093302940603L;

    public SecurityException(String message) {
        super(message);
    }
}
