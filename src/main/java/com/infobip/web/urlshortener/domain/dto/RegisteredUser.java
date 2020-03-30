package com.infobip.web.urlshortener.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUser implements Serializable {
    private static final long serialVersionUID = 4424175232246476320L;
    private String password;
    private Boolean success;
    private String description;
}
