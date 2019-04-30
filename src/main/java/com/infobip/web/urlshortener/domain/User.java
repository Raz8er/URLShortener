package com.infobip.web.urlshortener.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@JsonFilter("UserFilter")
public class User {

    @Id
    @Column(name = "account_id")
    private String accountId;
    @Transient
    @Column(name = "password")
    private String password;
    @Column(name = "hash_password")
    private String encodedPassword;
    @Column(name = "role")
    private String role;
    @Column(name = "success")
    private Boolean success;
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private List<Website> websites;
}
