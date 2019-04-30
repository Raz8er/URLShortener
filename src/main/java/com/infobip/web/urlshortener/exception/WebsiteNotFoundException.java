package com.infobip.web.urlshortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WebsiteNotFoundException extends RuntimeException {
    public WebsiteNotFoundException(String message) {
        super(message);
    }
}
