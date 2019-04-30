package com.infobip.web.urlshortener.repositories;

import com.infobip.web.urlshortener.domain.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, Long> {
    List<Website> findByAccount(String accountId);

    Website findWebsiteByShortUrl(String url);

    @Query("select w.url from Website w where w.account=?1 and w.url=?2")
    String findUrlByAccountAndUrl(String accountId, String url);

    @Query("select w.url from Website w where w.shortUrl=?1")
    String findUrlByShortUrl(String url);

    @Query("select w.redirectType from Website w where w.shortUrl=?1")
    Integer findRedirectTypeByShortUrl(String url);

    @Transactional
    @Modifying
    @Query("update Website w set w.count=?1 where w.shortUrl=?2")
    void updateWebsite(Integer count, String url);
}
