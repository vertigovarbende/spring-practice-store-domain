package com.deveyk.bookstore.book.controller.mapper;

import com.deveyk.bookstore.book.controller.request.BookSearchRequest;
import com.deveyk.bookstore.book.service.command.BookSearchCommand;
import com.deveyk.bookstore.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookSearchRequestToBookSearchCommandMapper extends BaseMapper<BookSearchRequest, BookSearchCommand> {

    BookSearchRequestToBookSearchCommandMapper INSTANCE = Mappers.getMapper(BookSearchRequestToBookSearchCommandMapper.class);

}
