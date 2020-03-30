package com.infobip.web.urlshortener.exception;

public class WebsiteNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3483061122032393940L;

    public WebsiteNotFoundException(String message) {
        super(message);
    }
}
