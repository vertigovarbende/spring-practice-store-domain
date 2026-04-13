package com.deveyk.bookstore.book.model.mapper;

import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.command.BookCreateCommand;
import com.deveyk.bookstore.book.service.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookApplicationMapper {

    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "authors", ignore = true)
    Book toDomain(BookCreateCommand command);

    Book toDomain(BookEntity entity);

    BookEntity toEntity(Book book);

}