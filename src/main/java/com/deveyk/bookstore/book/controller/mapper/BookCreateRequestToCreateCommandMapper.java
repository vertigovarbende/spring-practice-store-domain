package com.deveyk.bookstore.book.controller.mapper;

import com.deveyk.bookstore.book.controller.request.BookCreateRequest;
import com.deveyk.bookstore.book.service.command.BookCreateCommand;
import com.deveyk.bookstore.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookCreateRequestToCreateCommandMapper extends BaseMapper<BookCreateRequest, BookCreateCommand> {

    BookCreateRequestToCreateCommandMapper INSTANCE = Mappers.getMapper(BookCreateRequestToCreateCommandMapper.class);

}
