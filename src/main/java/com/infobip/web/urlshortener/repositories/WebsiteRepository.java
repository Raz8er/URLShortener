package com.infobip.web.urlshortener.repositories;

import com.infobip.web.urlshortener.domain.entity.WebsiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface WebsiteRepository extends JpaRepository<WebsiteEntity, Long> {
    List<WebsiteEntity> findByAccountId(String accountId);

    WebsiteEntity findWebsiteByShortUrl(String url);

    @Query("select w.url from WebsiteEntity w where w.accountId = ?1")
    String findUrlByAccountId(String accountId);

    @Transactional
    @Modifying
    @Query("update WebsiteEntity w set w.count = ?1 where w.shortUrl = ?2")
    void updateWebsite(Integer count, String shortUrl);
}
