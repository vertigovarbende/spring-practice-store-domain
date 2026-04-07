package com.deveyk.bookstore.book.controller.mapper;

import com.deveyk.bookstore.book.controller.response.BookSearchResponse;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookToBookResponseMapper extends BaseMapper<Book, BookSearchResponse> {

    BookToBookResponseMapper INSTANCE = Mappers.getMapper(BookToBookResponseMapper.class);

}
