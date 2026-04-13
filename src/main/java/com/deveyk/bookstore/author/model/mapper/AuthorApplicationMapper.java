package com.deveyk.bookstore.author.model.mapper;

import com.deveyk.bookstore.author.repository.entity.AuthorEntity;
import com.deveyk.bookstore.author.service.domain.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorApplicationMapper {

    Author toDomain(AuthorEntity entity);

}
