package com.infobip.web.urlshortener.services;

import com.infobip.web.urlshortener.domain.Website;
import com.infobip.web.urlshortener.exception.WebsiteNotFoundException;
import com.infobip.web.urlshortener.repositories.WebsiteRepository;
import com.infobip.web.urlshortener.utilities.RandomNumberUtility;
import com.infobip.web.urlshortener.utilities.RandomStringUtility;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebsiteService {

    private final WebsiteRepository websiteRepository;

    public WebsiteService(WebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
    }

    public Map<String, Integer> getWebsiteStatistics(String accountId) {
        List<Website> websites = websiteRepository.findByAccount(accountId);
        if (websites.isEmpty()) {
            throw new WebsiteNotFoundException("No websites found for user id - " + accountId);
        }
        Map<String, Integer> map = new HashMap<>();
        for (Website website : websites) {
            map.put(website.getUrl(), website.getCount());
        }
        return map;
    }

    public Website registerUrl(Website website, String currentActiveUsername) throws Exception {
        String existingUrl = websiteRepository.findUrlByAccountAndUrl(currentActiveUsername, website.getUrl());
        if (existingUrl != null && existingUrl.equals(website.getUrl())) {
            throw new Exception("Request url is already assigned to this user!");
        }
        if (website.getRedirectType() == null) {
            website.setRedirectType(302);
        }
        String shortenedUrl = "http://short.com/" + RandomStringUtility.randomString(RandomNumberUtility.getRandomInteger(5, 11));
        website.setShortUrl(shortenedUrl);
        website.setCount(0);
        website.setAccount(currentActiveUsername);
        websiteRepository.save(website);
        return website;
    }

    public String getWebsiteUrl(String shortUrl) {
        return websiteRepository.findUrlByShortUrl(shortUrl);
    }

    public Integer getWebsiteRedirectType(String shortUrl) {
        return websiteRepository.findRedirectTypeByShortUrl(shortUrl);
    }

    public void updateWebsiteCount(String shortUrl) throws Exception {
        Website website = websiteRepository.findWebsiteByShortUrl(shortUrl);
        if (website == null) {
            throw new Exception("There is no website registered for given url - " + shortUrl);
        }
        website.setCount(website.getCount() + 1);
        websiteRepository.updateWebsite(website.getCount(), shortUrl);
    }
}
