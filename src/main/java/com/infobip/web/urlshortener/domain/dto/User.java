package com.infobip.web.urlshortener.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -7313431470565185703L;
    @NotBlank(message = "accountId is mandatory")
    private String accountId;
}
