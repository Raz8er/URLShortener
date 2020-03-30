package com.infobip.web.urlshortener.mapping;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface BaseMapper<T, E> {

    T fromEntity(E entity);

    E toEntity(T model);

    void patchEntity(T model, @MappingTarget E entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void updateEntity(T model, @MappingTarget E entity);
}
