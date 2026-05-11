package com.deveyk.bookstore.author.model.mapper;

import com.deveyk.bookstore.author.repository.entity.AuthorEntity;
import com.deveyk.bookstore.author.service.command.AuthorCreateCommand;
import com.deveyk.bookstore.author.service.domain.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorApplicationMapper {

    // AuthorCreateCommand -> Author
    Author toDomain(AuthorCreateCommand command);

    // Author -> AuthorEntity
    Author toDomain(AuthorEntity entity);

    // AuthorEntity -> Author
    AuthorEntity toEntity(Author author);

}
