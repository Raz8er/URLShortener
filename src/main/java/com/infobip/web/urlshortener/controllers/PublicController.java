package com.infobip.web.urlshortener.controllers;

import com.infobip.web.urlshortener.services.WebsiteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class PublicController {
    private final WebsiteService websiteService;

    @GetMapping("/{shortUrl}")
    public void redirect(@PathVariable String shortUrl, HttpServletResponse httpServletResponse) {
        websiteService.redirect(shortUrl, httpServletResponse);
    }
}
