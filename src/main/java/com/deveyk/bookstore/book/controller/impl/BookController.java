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
    private final BookRequestMapper bookRequestMapper;
    private final BookResponseMapper bookResponseMapper;

    // POST - /api/v1/books - create()
    @PostMapping
    public BaseResponse<Void> create(@Valid @RequestBody BookCreateRequest request) {
        BookCreateCommand command = bookRequestMapper.toCreateCommand(request);
        bookService.create(command);
        return BaseResponse.successOf(HttpStatus.CREATED, "Book created successfully");
    }

    // GET - /api/v1/books/search - search() - first try for search strategy
    // refactor dto - request and response
    @GetMapping("/search")
    public BaseResponse<BsPageResponse<BookSearchResponse>> search(@Valid @RequestBody BookSearchRequest request) {
        BsPage<Book> bookPage = bookService.search(
                bookRequestMapper.toSearchCommand(request)
        );

        BsPageResponse<BookSearchResponse> pageResponse = BsPageResponse.<BookSearchResponse>builder()
                .content(bookPage.getContent()
                        .stream()
                        .map(bookResponseMapper::toSearchResponse)
                        .toList())
                .page(bookPage)
                .build();
        return BaseResponse.successOf(pageResponse);
    }

    // PUT - /api/v1/books/{bookId}/metadata - updateMetadata()
    @PutMapping("/{bookId}/metadata")
    public BaseResponse<Void> updateMetadata(
            @PathVariable @NotBlank(message = "Book id cannot be blank") String bookId,
            @Valid @RequestBody BookUpdateMetadataRequest request
    ) {
        BookUpdateMetadataCommand command = bookRequestMapper.toUpdateMetadataCommand(request, bookId);
        bookService.updateMetadata(command);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Book metadata updated successfully");
    }

    // DELETE - /api/v1/books/{bookId}
    @DeleteMapping("/{bookId}")
    public BaseResponse<Void> delete(@PathVariable @NotBlank(message = "Book id cannot be blank") String bookId) {
        bookService.delete(bookId);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Book deleted successfully");
    }

    // GET - /api/v1/books/deleted

    // DELETE - /api/v1/books/{bookId}/hard - not for all roles
    @DeleteMapping("/{bookId}/hard")
    public BaseResponse<Void> hardDelete(@PathVariable @NotBlank(message = "Book id cannot be blank") String bookId) {
        bookService.hardDelete(bookId);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Book permanently deleted");
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
        BookChangeStatusCommand command = bookRequestMapper.toChangeStatusCommand(request, bookId);
        bookService.changeStatus(command);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Book status changed successfully");
    }

    // POST - /api/v1/books/{bookId}/authors
    @PostMapping("/{bookId}/authors")
    public BaseResponse<Void> addAuthorToBook(
            @PathVariable
            @NotBlank(message = "Book id cannot be blank") String bookId,
            @Valid @RequestBody BookAddAuthorRequest request) {
        BookAddAuthorCommand command = bookRequestMapper.toAddAuthorCommand(request, bookId);
        bookService.addAuthorToBook(command);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Author added to book successfully");
    }

    // DELETE - /api/v1/books/{bookId}/authors
    // HİÇ AUTHOR'I KALMAYAN BOOK DELETED OLARAK MARKLANMALI MI?
    @DeleteMapping("/{bookId}/authors")
    public BaseResponse<Void> removeAuthorFromBook(
            @PathVariable
            @NotBlank(message = "Book id cannot be blank") String bookId,
            @Valid @RequestBody BookRemoveAuthorRequest request
    ) {
        BookRemoveAuthorCommand command = bookRequestMapper.toRemoveAuthorCommand(request, bookId);
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
        BookAddGenreCommand command = bookRequestMapper.toAddGenreCommand(request, bookId);
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
        BookRemoveGenreCommand command = bookRequestMapper.toRemoveGenreCommand(request, bookId);
        bookService.removeGenreFromBook(command);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Genre removed from book successfully");
    }
}
