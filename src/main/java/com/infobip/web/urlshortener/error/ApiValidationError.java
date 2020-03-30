package com.infobip.web.urlshortener.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError implements Serializable {
    private static final long serialVersionUID = 9196833976570125051L;
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
