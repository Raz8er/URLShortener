package com.infobip.web.urlshortener.repositories;

import com.infobip.web.urlshortener.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByAccountId(String accountId);
}
