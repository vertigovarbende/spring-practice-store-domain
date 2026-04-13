package com.deveyk.bookstore.book.controller.mapper;

import com.deveyk.bookstore.book.controller.request.BookRemoveGenreRequest;
import com.deveyk.bookstore.book.service.command.BookRemoveGenreCommand;
import com.deveyk.bookstore.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookRemoveGenreRequestToBookRemoveGenreCommandMapper extends BaseMapper<BookRemoveGenreRequest, BookRemoveGenreCommand> {

    BookRemoveGenreRequestToBookRemoveGenreCommandMapper INSTANCE = Mappers.getMapper(BookRemoveGenreRequestToBookRemoveGenreCommandMapper.class);

    BookRemoveGenreCommand map(BookRemoveGenreRequest request, String bookId);

}
