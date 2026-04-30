package com.deveyk.bookstore.book.model.mapper;

import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.command.BookCreateCommand;
import com.deveyk.bookstore.book.service.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookApplicationMapper {

    // BookCreateCommand -> Book
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "category", ignore = true)
    Book toDomain(BookCreateCommand command);

    // BookEntity -> Book
    Book toDomain(BookEntity entity);

    // Book -> BookEntity
    BookEntity toEntity(Book book);

}