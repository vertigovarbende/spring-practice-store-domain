package com.deveyk.bookstore.book.model.mapper;

import com.deveyk.bookstore.book.service.command.BookCreateCommand;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookCreateCommandToDomainMapper extends BaseMapper<BookCreateCommand, Book> {

    BookCreateCommandToDomainMapper INSTANCE = Mappers.getMapper(BookCreateCommandToDomainMapper.class);


}
