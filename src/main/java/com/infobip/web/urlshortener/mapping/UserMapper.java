package com.infobip.web.urlshortener.mapping;

import com.infobip.web.urlshortener.domain.dto.User;
import com.infobip.web.urlshortener.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class UserMapper implements BaseMapper<User, UserEntity> {

    public abstract User fromEntity(UserEntity entity);

    public abstract UserEntity toEntity(User model);
}
