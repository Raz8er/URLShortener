package com.infobip.web.urlshortener.controllers;

import com.infobip.web.urlshortener.services.WebsiteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class PublicController {
    private final WebsiteService websiteService;

    public PublicController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @GetMapping
    public void redirect(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        String shortUrl = request.getRequestURL().toString().split("8080/")[1];
        if (shortUrl == null || shortUrl.equals("")) {
            throw new Exception("Url is not provided or it is empty!");
        }
        websiteService.updateWebsiteCount(shortUrl);
        String redirectUrl = websiteService.getWebsiteUrl(shortUrl);
        Integer status = websiteService.getWebsiteRedirectType(shortUrl);
        httpServletResponse.setHeader("Location", redirectUrl);
        httpServletResponse.setStatus(status);
    }
}
