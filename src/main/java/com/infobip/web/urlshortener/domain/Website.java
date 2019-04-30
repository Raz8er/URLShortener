package com.infobip.web.urlshortener.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "websites")
@JsonFilter("WebsiteFilter")
public class Website {

    @Id
    @Column(name = "shortUrl")
    private String shortUrl;
    @Column(name = "url")
    private String url;
    @Column(name = "count")
    private Integer count;
    @Column(name = "account_id")
    private String account;
    @Column(name = "redirect_type")
    private Integer redirectType;

}
