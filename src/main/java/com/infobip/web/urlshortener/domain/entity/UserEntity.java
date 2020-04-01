package com.infobip.web.urlshortener.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String accountId;
    private String hashPassword;
    private String role;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "user_website",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "website_id")
    )
    private Set<WebsiteEntity> websites = new HashSet<>();

    public void addWebsite(WebsiteEntity website) {
        websites.add(website);
        website.getUsers().add(this);
    }
}
