package com.infobip.web.urlshortener.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredWebsite implements Serializable {
    private static final long serialVersionUID = 6890690764619708408L;
    private String shortUrl;
}
