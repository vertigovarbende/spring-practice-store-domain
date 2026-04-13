package com.deveyk.bookstore.book.controller.mapper;

import com.deveyk.bookstore.book.controller.request.BookAddAuthorRequest;
import com.deveyk.bookstore.book.service.command.BookAddAuthorCommand;
import com.deveyk.bookstore.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookAddAuthorRequestToBookAddAuthorCommandMapper extends BaseMapper<BookAddAuthorRequest, BookAddAuthorCommand> {

    BookAddAuthorRequestToBookAddAuthorCommandMapper INSTANCE = Mappers.getMapper(BookAddAuthorRequestToBookAddAuthorCommandMapper.class);

    BookAddAuthorCommand map(BookAddAuthorRequest request, String bookId);

}
