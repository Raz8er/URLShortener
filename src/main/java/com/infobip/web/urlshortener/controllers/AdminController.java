package com.infobip.web.urlshortener.controllers;

import com.infobip.web.urlshortener.domain.dto.RegisteredWebsite;
import com.infobip.web.urlshortener.domain.dto.Website;
import com.infobip.web.urlshortener.domain.dto.WebsiteStatistics;
import com.infobip.web.urlshortener.services.WebsiteService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(AdminController.BASE_URL)
public class AdminController {

    public static final String BASE_URL = "/api/v1/shortener/admin";
    private final WebsiteService websiteService;

    @PostMapping("/register")
    public RegisteredWebsite registerUrl(@RequestBody @Valid Website website, Authentication authentication) {
        return this.websiteService.registerUrl(website, authentication.getName());
    }

    @GetMapping("/statistic/{accountId}")
    public WebsiteStatistics getWebsiteStatistics(@PathVariable String accountId, Authentication authentication) {
        return websiteService.getWebsiteStatistics(accountId, authentication);
    }
}
