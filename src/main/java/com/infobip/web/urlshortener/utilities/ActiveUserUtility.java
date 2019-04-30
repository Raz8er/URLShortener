package com.infobip.web.urlshortener.utilities;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class ActiveUserUtility {
    public static String getActiveUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentActiveUsername;
        if (principal instanceof UserDetails) {
            currentActiveUsername = ((UserDetails)principal).getUsername();
        } else {
            currentActiveUsername = principal.toString();
        }
        return currentActiveUsername;
    }
}
