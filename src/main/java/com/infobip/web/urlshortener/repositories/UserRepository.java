package com.infobip.web.urlshortener.repositories;

import com.infobip.web.urlshortener.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByAccountId(String accountId);
}
