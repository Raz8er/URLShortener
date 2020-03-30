package com.infobip.web.urlshortener.exception;

public class InvalidUrlException extends RuntimeException {
    private static final long serialVersionUID = 2968384029440292862L;

    public InvalidUrlException(String message) {
        super(message);
    }
}
