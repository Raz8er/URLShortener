package com.infobip.web.urlshortener.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CheckUrlUtility {
    private static final UrlValidator URL_VALIDATOR = new UrlValidator();

    public static boolean validateUrl(String url) {
        return URL_VALIDATOR.isValid(url);
    }
}
