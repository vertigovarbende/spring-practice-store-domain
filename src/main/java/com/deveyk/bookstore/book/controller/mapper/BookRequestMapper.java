package com.deveyk.bookstore.book.controller.mapper;

import com.deveyk.bookstore.book.controller.request.*;
import com.deveyk.bookstore.book.service.command.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookRequestMapper {

    // BookCreateRequest -> BookCreateCommand
    BookCreateCommand toCreateCommand(BookCreateRequest request);

    // BookSearchRequest -> BookSearchCommand
    BookSearchCommand toSearchCommand(BookSearchRequest request);

    // BookAddAuthorRequest -> BookAddAuthorCommand
    @Mapping(target = "bookId", source = "bookId")
    @Mapping(target = "authorIds", source = "request.authorIds")
    BookAddAuthorCommand toAddAuthorCommand(BookAddAuthorRequest request, String bookId);

    // BookRemoveAuthorRequest -> BookRemoveAuthorCommand
    @Mapping(target = "bookId", source = "bookId")
    @Mapping(target = "authorIds", source = "request.authorIds")
    BookRemoveAuthorCommand toRemoveAuthorCommand(BookRemoveAuthorRequest request, String bookId);

    // BookAddGenreRequest -> BookAddGenreCommand
    @Mapping(target = "bookId", source = "bookId")
    @Mapping(target = "genres", source = "request.genres")
    BookAddGenreCommand toAddGenreCommand(BookAddGenreRequest request, String bookId);

    // BookRemoveGenreRequest -> BookRemoveGenreCommand
    @Mapping(target = "bookId", source = "bookId")
    @Mapping(target = "genres", source = "request.genres")
    BookRemoveGenreCommand toRemoveGenreCommand(BookRemoveGenreRequest request, String bookId);

    // BookChangeStatusRequest -> BookChangeStatusCommand
    @Mapping(target = "bookId", source = "bookId")
    @Mapping(target = "targetStatus", source = "request.targetStatus")
    BookChangeStatusCommand toChangeStatusCommand(BookChangeStatusRequest request, String bookId);

}