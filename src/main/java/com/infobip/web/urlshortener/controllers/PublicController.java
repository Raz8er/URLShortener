package com.infobip.web.urlshortener.controllers;

import com.infobip.web.urlshortener.services.WebsiteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PublicController {
    private final WebsiteService websiteService;

    public PublicController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @GetMapping("/{shortUrl}")
    public void redirect(@PathVariable String shortUrl, HttpServletResponse httpServletResponse) throws Exception {
        if (shortUrl == null || shortUrl.equals("")) {
            throw new Exception("Url is not provided or it is empty!");
        }
        shortUrl = "http://short.com/" + shortUrl;
        websiteService.updateWebsiteCount(shortUrl);
        String redirectUrl = websiteService.getWebsiteUrl(shortUrl);
        Integer status = websiteService.getWebsiteRedirectType(shortUrl);
        httpServletResponse.setHeader("Location", redirectUrl);
        httpServletResponse.setStatus(status);
    }
}
