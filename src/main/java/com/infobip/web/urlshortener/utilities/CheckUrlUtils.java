package com.infobip.web.urlshortener.utilities;

import org.apache.commons.validator.routines.UrlValidator;

public final class CheckUrlUtils {

    private static final UrlValidator URL_VALIDATOR = new UrlValidator();

    private CheckUrlUtils() {
    }

    public static boolean validateUrl(String url) {
        return URL_VALIDATOR.isValid(url);
    }
}
