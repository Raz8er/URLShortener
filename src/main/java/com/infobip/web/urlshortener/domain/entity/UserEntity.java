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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountId;
    private String hashPassword;
    private String role;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_website",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "website_id")
    )
    private Set<WebsiteEntity> websites = new HashSet<>();
}
