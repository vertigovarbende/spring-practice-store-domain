package com.deveyk.bookstore.book.controller.mapper;

import com.deveyk.bookstore.book.controller.response.BookSearchResponse;
import com.deveyk.bookstore.book.service.domain.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookResponseMapper {

    // Book -> BookSearchResponse
    BookSearchResponse toSearchResponse(Book book);

}
