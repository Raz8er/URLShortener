package com.infobip.web.urlshortener.controllers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.infobip.web.urlshortener.domain.Website;
import com.infobip.web.urlshortener.services.WebsiteService;
import com.infobip.web.urlshortener.utilities.CheckUrlUtility;
import com.infobip.web.urlshortener.utilities.MappingValueUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(AdminController.BASE_URL)
public class AdminController {
    static final String BASE_URL = "/api/v1/shortener/admin";
    private final WebsiteService websiteService;

    public AdminController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public MappingJacksonValue registerUrl(@RequestBody Website website, Authentication authentication) throws Exception {
        if (website.getUrl() == null || website.getUrl().equals("")) {
            throw new Exception("Url must not be null or empty string!");
        }
        if (!CheckUrlUtility.checkUrl(website.getUrl())) {
            throw new Exception("Url - " + website.getUrl() + " is not valid!");
        }
        String activeUser = authentication.getName();
        Website tmpWebsite = websiteService.registerUrl(website, activeUser);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("shortUrl");
        return MappingValueUtility.mapValue("WebsiteFilter", filter, tmpWebsite);
    }

    @GetMapping("/statistic/{AccountId}")
    public Map<String, Integer> findAllWebsitesById(@PathVariable String AccountId, Authentication authentication) throws Exception {
        if (AccountId == null || AccountId.equals("")) {
            throw new Exception("Account id in request must not be null or empty!");
        }
        String activeUser = authentication.getName();
        if (!AccountId.equals(activeUser)) {
            throw new Exception("Authenticated user is requesting statistic details of another user!");
        }
        return websiteService.getWebsiteStatistics(AccountId);
    }
}
