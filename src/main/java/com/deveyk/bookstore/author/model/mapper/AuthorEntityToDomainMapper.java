package com.deveyk.bookstore.author.model.mapper;

import com.deveyk.bookstore.author.repository.entity.AuthorEntity;
import com.deveyk.bookstore.author.service.domain.Author;
import com.deveyk.bookstore.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorEntityToDomainMapper extends BaseMapper<AuthorEntity, Author> {

    AuthorEntityToDomainMapper INSTANCE = Mappers.getMapper(AuthorEntityToDomainMapper.class);

}
