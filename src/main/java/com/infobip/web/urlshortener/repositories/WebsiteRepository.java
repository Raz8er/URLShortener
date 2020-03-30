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

    @Query(value = "select w.url from website w where w.account_id = :accountId", nativeQuery = true)
    String findUrlByAccountId(String accountId);

    @Transactional
    @Modifying
    @Query(value = "update website w set w.count = :count where w.short_url = :url", nativeQuery = true)
    void updateWebsite(Integer count, String url);
}
