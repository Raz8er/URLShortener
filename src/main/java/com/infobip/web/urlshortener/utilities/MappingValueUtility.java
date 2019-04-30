package com.infobip.web.urlshortener.utilities;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

public class MappingValueUtility {
    public static MappingJacksonValue mapValue(String domainFilter, SimpleBeanPropertyFilter simpleBeanPropertyFilter, Object obj) {
        FilterProvider filters = new SimpleFilterProvider().addFilter(domainFilter, simpleBeanPropertyFilter);
        MappingJacksonValue mapping = new MappingJacksonValue(obj);
        mapping.setFilters(filters);
        return mapping;
    }
}
