package com.infobip.web.urlshortener.utilities;

import org.apache.commons.validator.routines.UrlValidator;

public class CheckUrlUtility {
    public static boolean checkUrl(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}
