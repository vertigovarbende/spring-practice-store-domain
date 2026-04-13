package com.deveyk.bookstore.book.controller.impl;

import com.deveyk.bookstore.book.controller.mapper.*;
import com.deveyk.bookstore.book.controller.request.*;
import com.deveyk.bookstore.book.controller.response.BookSearchResponse;
import com.deveyk.bookstore.book.service.IBookService;
import com.deveyk.bookstore.book.service.command.*;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.common.controller.response.BaseResponse;
import com.deveyk.bookstore.common.controller.response.BsPageResponse;
import com.deveyk.bookstore.common.model.BsPage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    private static final BookToBookResponseMapper BOOK_TO_BOOK_RESPONSE_MAPPER = BookToBookResponseMapper.INSTANCE;
    private static final BookAddAuthorRequestToBookAddAuthorCommandMapper BOOK_ADD_AUTHOR_REQUEST_TO_ADD_AUTHOR_TO_BOOK_COMMAND_MAPPER = BookAddAuthorRequestToBookAddAuthorCommandMapper.INSTANCE;
    private static final BookRemoveAuthorRequestToBookRemoveAuthorCommandMapper BOOK_REMOVE_AUTHOR_REQUEST_TO_BOOK_REMOVE_AUTHOR_COMMAND_MAPPER = BookRemoveAuthorRequestToBookRemoveAuthorCommandMapper.INSTANCE;
    private static final BookAddGenreRequestToBookAddGenreCommandMapper BOOK_ADD_GENRE_REQUEST_TO_BOOK_ADD_GENRE_COMMAND_MAPPER = BookAddGenreRequestToBookAddGenreCommandMapper.INSTANCE;
    private static final BookRemoveGenreRequestToBookRemoveGenreCommandMapper BOOK_REMOVE_GENRE_REQUEST_TO_BOOK_REMOVE_GENRE_COMMAND_MAPPER = BookRemoveGenreRequestToBookRemoveGenreCommandMapper.INSTANCE;
    private static final BookSearchRequestToBookSearchCommandMapper BOOK_SEARCH_REQUEST_TO_BOOK_SEARCH_COMMAND_MAPPER = BookSearchRequestToBookSearchCommandMapper.INSTANCE;
    private static final BookCreateRequestToCreateCommandMapper BOOK_CREATE_REQUEST_TO_CREATE_COMMAND_MAPPER = BookCreateRequestToCreateCommandMapper.INSTANCE;
    private static final BookChangeStatusRequestToBookChangeStatusCommandMapper BOOK_CHANGE_STATUS_REQUEST_TO_BOOK_CHANGE_STATUS_COMMAND_MAPPER = BookChangeStatusRequestToBookChangeStatusCommandMapper.INSTANCE;

    // GET - /api/v1/books/search - search() - first try for search strategy
    // refactor dto - request and response
    @GetMapping("/search")
    public BaseResponse<BsPageResponse<BookSearchResponse>> search(@Valid @RequestBody BookSearchRequest request) {
        BsPage<Book> bookPage = bookService.search(
                BOOK_SEARCH_REQUEST_TO_BOOK_SEARCH_COMMAND_MAPPER.map(request)
        );

        BsPageResponse<BookSearchResponse> pageResponse = BsPageResponse.<BookSearchResponse>builder()
                .content(bookPage.getContent()
                        .stream()
                        .map(BOOK_TO_BOOK_RESPONSE_MAPPER::map)
                        .toList())
                .page(bookPage)
                .build();
        return BaseResponse.successOf(pageResponse);
    }

    // POST - /api/v1/books - create()
    @PostMapping
    public BaseResponse<Void> create(@Valid @RequestBody BookCreateRequest request) {
        BookCreateCommand command = BOOK_CREATE_REQUEST_TO_CREATE_COMMAND_MAPPER.map(request);
        bookService.create(command);
        return BaseResponse.successOf(HttpStatus.CREATED, "Book created successfully");
    }

    // DELETE - /api/v1/books/{bookId}
    @DeleteMapping("/{bookId}")
    public BaseResponse<Void> delete(@PathVariable String bookId) {
        bookService.delete(bookId);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Book deleted successfully");
    }

    // PATCH - /api/v1/books/{bookId}/restore
    @PatchMapping("/{bookId}/restore")
    public BaseResponse<Void> restore(@PathVariable String bookId) {
        bookService.restore(bookId);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Book restored successfully");
    }

    // PATCH - /api/v1/books/{bookId}/status
    @PatchMapping("/{bookId}/status")
    public BaseResponse<Void> changeStatus(
            @PathVariable
            @NotBlank(message = "Book id cannot be blank") String bookId,
            @Valid @RequestBody BookChangeStatusRequest request
    ) {
        BookChangeStatusCommand command = BOOK_CHANGE_STATUS_REQUEST_TO_BOOK_CHANGE_STATUS_COMMAND_MAPPER.map(request, bookId);
        bookService.changeStatus(command);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Book status changed successfully");
    }

    // POST - /api/v1/books/{bookId}/authors
    @PostMapping("/{bookId}/authors")
    public BaseResponse<Void> addAuthorToBook(
            @PathVariable
            @NotBlank(message = "Book id cannot be blank") String bookId,
            @Valid @RequestBody BookAddAuthorRequest request) {
        BookAddAuthorCommand command = BOOK_ADD_AUTHOR_REQUEST_TO_ADD_AUTHOR_TO_BOOK_COMMAND_MAPPER.map(request, bookId);
        bookService.addAuthorToBook(command);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Author added to book successfully");
    }

    // DELETE - /api/v1/books/{bookId}/authors
    @DeleteMapping("/{bookId}/authors")
    public BaseResponse<Void> removeAuthorFromBook(
            @PathVariable
            @NotBlank(message = "Book id cannot be blank") String bookId,
            @Valid @RequestBody BookRemoveAuthorRequest request
    ) {
        BookRemoveAuthorCommand command = BOOK_REMOVE_AUTHOR_REQUEST_TO_BOOK_REMOVE_AUTHOR_COMMAND_MAPPER.map(request, bookId);
        bookService.removeAuthorFromBook(command);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Author removed from book successfully");
    }

    // POST - /api/v1/books/{bookId}/genres
    @PostMapping("/{bookId}/genres")
    public BaseResponse<Void> addGenreToBook(
            @PathVariable
            @NotBlank(message = "Book id cannot be blank") String bookId,
            @Valid @RequestBody BookAddGenreRequest request
    ) {
        BookAddGenreCommand command = BOOK_ADD_GENRE_REQUEST_TO_BOOK_ADD_GENRE_COMMAND_MAPPER.map(request, bookId);
        bookService.addGenreToBook(command);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Genre added to book successfully");
    }

    // DELETE - /api/v1/books/{bookId}/genres
    @DeleteMapping("/{bookId}/genres")
    public BaseResponse<Void> removeGenreFromBook(
            @PathVariable
            @NotBlank(message = "Book id cannot be blank") String bookId,
            @Valid @RequestBody BookRemoveGenreRequest request
    ) {
        BookRemoveGenreCommand command = BOOK_REMOVE_GENRE_REQUEST_TO_BOOK_REMOVE_GENRE_COMMAND_MAPPER.map(request, bookId);
        bookService.removeGenreFromBook(command);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Genre removed from book successfully");
    }


}
