package com.infobip.web.urlshortener.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "website")
public class WebsiteEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String shortUrl;
    private String url;
    private Integer count = 0;
    private String accountId;
    private Integer redirectType;
    @ManyToMany(mappedBy = "websites")
    private Set<UserEntity> users = new HashSet<>();
}
