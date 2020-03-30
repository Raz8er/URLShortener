package com.infobip.web.urlshortener.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Website implements Serializable {
    private static final long serialVersionUID = -287019755682673588L;
    @NotBlank(message = "url is mandatory")
    private String url;
    private Integer redirectType = 302;
}
