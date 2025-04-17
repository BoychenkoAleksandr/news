package com.boic.testTask.users;

import com.boic.testTask.common.DateMapper;
import com.boic.testTask.common.JpaMapper;
import com.boic.testTask.news.NewsDtoOut;
import com.boic.testTask.news.NewsJpa;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = DateMapper.class)
public interface UserMapper extends JpaMapper<UserDtoOut, UserJpa> {
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    NewsDtoOut fromJpaEntity(NewsJpa jpaEntity);
}
