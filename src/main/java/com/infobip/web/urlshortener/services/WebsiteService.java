package com.infobip.web.urlshortener.services;

import com.infobip.web.urlshortener.domain.dto.RegisteredWebsite;
import com.infobip.web.urlshortener.domain.dto.Website;
import com.infobip.web.urlshortener.domain.dto.WebsiteStatistics;
import com.infobip.web.urlshortener.domain.entity.WebsiteEntity;
import com.infobip.web.urlshortener.exception.InvalidUrlException;
import com.infobip.web.urlshortener.exception.UrlAlreadyAssignedException;
import com.infobip.web.urlshortener.exception.WebsiteNotFoundException;
import com.infobip.web.urlshortener.mapping.WebsiteMapper;
import com.infobip.web.urlshortener.repositories.WebsiteRepository;
import com.infobip.web.urlshortener.utilities.CheckUrlUtils;
import com.infobip.web.urlshortener.utilities.EnvUtil;
import com.infobip.web.urlshortener.utilities.RandomNumberUtils;
import com.infobip.web.urlshortener.utilities.RandomStringUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class WebsiteService {
    private final WebsiteRepository websiteRepository;
    private final WebsiteMapper websiteMapper;
    private final EnvUtil envUtil;

    public RegisteredWebsite registerUrl(Website website, String accountId) {
        if (!CheckUrlUtils.validateUrl(website.getUrl())) {
            throw new InvalidUrlException(String.format("Url '%s' is not valid", website.getUrl()));
        }

        String existingUrl = websiteRepository.findUrlByAccountId(accountId);
        if (Objects.nonNull(existingUrl) && existingUrl.equals(website.getUrl())) {
            throw new UrlAlreadyAssignedException(String.format("Url '%s' already assigned to this user", existingUrl));
        }

        WebsiteEntity entity = this.websiteMapper.toEntity(website);
        String randomString = RandomStringUtils.randomString(RandomNumberUtils.getRandomInteger(5, 11));
        entity.setShortUrl(randomString);
        entity.setAccountId(accountId);
        WebsiteEntity savedEntity = this.websiteRepository.save(entity);

        return new RegisteredWebsite(envUtil.getServerUrlPrefix() + savedEntity.getShortUrl());
    }

    public WebsiteStatistics getWebsiteStatistics(String accountId, Authentication authentication) {
        WebsiteStatistics websiteStatistics = new WebsiteStatistics();

        validateRequest(accountId, authentication);

        List<WebsiteEntity> websites = websiteRepository.findByAccountId(accountId);
        if (websites.isEmpty()) {
            throw new WebsiteNotFoundException(String.format("No websites found for user id '%s'", accountId));
        }

        websites.forEach(website -> websiteStatistics.getStatisticsMap().put(website.getUrl(), website.getCount()));

        return websiteStatistics;
    }

    public void redirect(String shortUrl, HttpServletResponse httpServletResponse) {
        WebsiteEntity website = websiteRepository.findWebsiteByShortUrl(shortUrl);
        if (Objects.isNull(website)) {
            throw new WebsiteNotFoundException(String.format("No website registered for url '%s'", shortUrl));
        }

        this.updateWebsiteCount(website);
        httpServletResponse.setHeader("Location", website.getUrl());
        httpServletResponse.setStatus(website.getRedirectType());
    }

    private void validateRequest(String accountId, Authentication authentication) {
        Assert.hasLength(accountId, "Account id must not be null or empty");
        String activeUser = authentication.getName();
        if (!accountId.equals(activeUser)) {
            throw new SecurityException("Unauthorized user requesting statistics");
        }
    }

    private void updateWebsiteCount(WebsiteEntity website) {
        website.setCount(website.getCount() + 1);
        websiteRepository.updateWebsite(website.getCount(), website.getShortUrl());
    }
}
