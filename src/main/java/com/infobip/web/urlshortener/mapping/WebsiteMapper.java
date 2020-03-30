package com.infobip.web.urlshortener.mapping;

import com.infobip.web.urlshortener.domain.dto.Website;
import com.infobip.web.urlshortener.domain.entity.WebsiteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class WebsiteMapper implements BaseMapper<Website, WebsiteEntity> {

    public abstract Website fromEntity(WebsiteEntity entity);

    public abstract WebsiteEntity toEntity(Website model);
}
