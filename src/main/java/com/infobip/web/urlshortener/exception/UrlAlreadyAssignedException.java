package com.infobip.web.urlshortener.exception;

public class UrlAlreadyAssignedException extends RuntimeException {
    private static final long serialVersionUID = -8340421373971707491L;

    public UrlAlreadyAssignedException(String message) {
        super(message);
    }
}
