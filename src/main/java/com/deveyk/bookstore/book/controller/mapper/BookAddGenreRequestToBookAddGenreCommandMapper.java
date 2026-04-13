package com.deveyk.bookstore.book.controller.mapper;

import com.deveyk.bookstore.book.controller.request.BookAddGenreRequest;
import com.deveyk.bookstore.book.service.command.BookAddGenreCommand;
import com.deveyk.bookstore.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookAddGenreRequestToBookAddGenreCommandMapper extends BaseMapper<BookAddGenreRequest, BookAddGenreCommand> {

    BookAddGenreRequestToBookAddGenreCommandMapper INSTANCE = Mappers.getMapper(BookAddGenreRequestToBookAddGenreCommandMapper.class);

    BookAddGenreCommand map(BookAddGenreRequest request, String bookId);

}
