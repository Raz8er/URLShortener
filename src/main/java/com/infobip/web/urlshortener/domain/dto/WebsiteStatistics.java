package com.infobip.web.urlshortener.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebsiteStatistics implements Serializable {
    private static final long serialVersionUID = -2257014444924955688L;
    private Map<String, Integer> statisticsMap = new HashMap<>();
}
