package com.infobip.web.urlshortener.exception;

public class HostnameException extends RuntimeException {
    private static final long serialVersionUID = 2129526768887347396L;

    public HostnameException(String message, Throwable cause) {
        super(message, cause);
    }
}
