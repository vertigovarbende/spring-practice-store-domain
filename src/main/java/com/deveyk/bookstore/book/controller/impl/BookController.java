package com.deveyk.bookstore.book.controller.impl;

import com.deveyk.bookstore.book.controller.mapper.BookAddAuthorRequestToAddAuthorToBookCommandMapper;
import com.deveyk.bookstore.book.controller.mapper.BookSearchRequestToBookSearchCommandMapper;
import com.deveyk.bookstore.book.controller.mapper.BookToBookResponseMapper;
import com.deveyk.bookstore.book.controller.request.BookAddAuthorRequest;
import com.deveyk.bookstore.book.controller.request.BookAddGenreRequest;
import com.deveyk.bookstore.book.controller.request.BookSearchRequest;
import com.deveyk.bookstore.book.controller.response.BookSearchResponse;
import com.deveyk.bookstore.book.service.IBookService;
import com.deveyk.bookstore.book.service.command.AddAuthorToBookCommand;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.common.controller.response.BaseResponse;
import com.deveyk.bookstore.common.controller.response.BsPageResponse;
import com.deveyk.bookstore.common.model.BsPage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    private static final BookToBookResponseMapper BOOK_TO_BOOK_RESPONSE_MAPPER = BookToBookResponseMapper.INSTANCE;
    private static final BookAddAuthorRequestToAddAuthorToBookCommandMapper BOOK_ADD_AUTHOR_REQUEST_TO_ADD_AUTHOR_TO_BOOK_COMMAND_MAPPER = BookAddAuthorRequestToAddAuthorToBookCommandMapper.INSTANCE;
    private static final BookSearchRequestToBookSearchCommandMapper BOOK_SEARCH_REQUEST_TO_BOOK_SEARCH_COMMAND_MAPPER = BookSearchRequestToBookSearchCommandMapper.INSTANCE;

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
        return BaseResponse.of(pageResponse);
    }

    // POST - /api/v1/books/{bookId}/authors - revised implementation to add an author to a book and verify functionality in Book domain
    // refactor dto - request
    @PostMapping("/{bookId}/authors")
    public BaseResponse<Void> addAuthorToBook(
            @PathVariable String bookId,
            @Valid @RequestBody BookAddAuthorRequest request) {
        AddAuthorToBookCommand command = BOOK_ADD_AUTHOR_REQUEST_TO_ADD_AUTHOR_TO_BOOK_COMMAND_MAPPER.map(request);
        bookService.addAuthorToBook(bookId, command);
        return BaseResponse.SUCCESS;
    }

    // DELETE - /api/v1/books/{bookId}/authors/{authorId} - revised implementation to remove an author to a book and verify functionality in Book domain
    // create dto - request
    @DeleteMapping("/{bookId}/authors/{authorId}")
    public BaseResponse<Void> removeAuthorFromBook(
            @PathVariable String bookId,
            @PathVariable String authorId
    ) {
        bookService.removeAuthorFromBook(bookId, authorId);
        return BaseResponse.SUCCESS;
    }

    // POST - /api/v1/books/{bookId}/genres - revised implementation to add a genre to a book and verify functionality in Book domain
    // refactor dto - request
    @PostMapping("/{bookId}/genres")
    public BaseResponse<Void> addGenreToBook(
            @PathVariable String bookId,
            @Valid @RequestBody BookAddGenreRequest request) {
        bookService.addGenreToBook(bookId, request);
        return BaseResponse.SUCCESS;
    }

    // DELETE - /api/v1/books/{bookId}/genres - revised implementation to remove a genre to a book and verify functionality in Book domain
    // change dto - request is wrong
    @DeleteMapping("/{bookId}/genres")
    public BaseResponse<Void> removeGenreFromBook(
            @PathVariable String bookId,
            @Valid @RequestBody BookAddGenreRequest request) {
        bookService.removeGenreFromBook(bookId, request);
        return BaseResponse.SUCCESS;
    }


}
