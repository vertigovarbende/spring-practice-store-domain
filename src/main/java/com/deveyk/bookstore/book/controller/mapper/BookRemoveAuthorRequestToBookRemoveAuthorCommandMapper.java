package com.deveyk.bookstore.book.controller.mapper;

import com.deveyk.bookstore.book.controller.request.BookRemoveAuthorRequest;
import com.deveyk.bookstore.book.service.command.BookRemoveAuthorCommand;
import com.deveyk.bookstore.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookRemoveAuthorRequestToBookRemoveAuthorCommandMapper extends BaseMapper<BookRemoveAuthorRequest, BookRemoveAuthorCommand> {

    BookRemoveAuthorRequestToBookRemoveAuthorCommandMapper INSTANCE = Mappers.getMapper(BookRemoveAuthorRequestToBookRemoveAuthorCommandMapper.class);

    BookRemoveAuthorCommand map(BookRemoveAuthorRequest request, String bookId);

}
