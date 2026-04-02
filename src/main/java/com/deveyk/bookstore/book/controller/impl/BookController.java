package com.deveyk.bookstore.book.controller.impl;

import com.deveyk.bookstore.book.controller.mapper.BookToBookResponseMapper;
import com.deveyk.bookstore.book.controller.request.BookAddAuthorRequest;
import com.deveyk.bookstore.book.controller.request.BookAddGenreRequest;
import com.deveyk.bookstore.book.controller.request.BookSearchRequest;
import com.deveyk.bookstore.book.controller.response.BookResponse;
import com.deveyk.bookstore.book.service.IBookService;
import com.deveyk.bookstore.book.service.impl.BookService;
import com.deveyk.bookstore.common.controller.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    private static final BookToBookResponseMapper bookToBookResponseMapper = BookToBookResponseMapper.INSTANCE;

    // GET - /api/v1/books/search - search() - first try for search strategy
    // refactor dto - request
    @GetMapping("/search")
    public BaseResponse<List<BookResponse>> search(@Valid @RequestBody BookSearchRequest request) {
        List<BookResponse> bookResponses = bookToBookResponseMapper.mapList(bookService.search(request));
        return BaseResponse.of(bookResponses);
    }

    // POST - /api/v1/books/{bookId}/authors - revised implementation to add an author to a book and verify functionality in Book domain
    // refactor dto - request
    @PostMapping("/{bookId}/authors")
    public BaseResponse<Void> addAuthorToBook(
            @PathVariable("bookId") String id,
            @Valid @RequestBody BookAddAuthorRequest request) {
        bookService.addAuthorToBook(id, request);
        return BaseResponse.SUCCESS;
    }

    // DELETE - /api/v1/books/{bookId}/authors/{authorId} - revised implementation to remove an author to a book and verify functionality in Book domain
    // create dto - request
    @DeleteMapping("/{bookId}/authors/{authorId}")
    public BaseResponse<Void> removeAuthorFromBook(
            @PathVariable("bookId") String bookId,
            @PathVariable("authorId") String authorId
    ) {
        bookService.removeAuthorFromBook(bookId, authorId);
        return BaseResponse.SUCCESS;
    }

    // POST - /api/v1/books/{bookId}/genres - revised implementation to add a genre to a book and verify functionality in Book domain
    // refactor dto - request
    @PostMapping("/{bookId}/genres")
    public BaseResponse<Void> addGenreToBook(
            @PathVariable("bookId") String bookId,
            @Valid @RequestBody BookAddGenreRequest request) {
        bookService.addGenreToBook(bookId, request);
        return BaseResponse.SUCCESS;
    }

    // DELETE - /api/v1/books/{bookId}/genres - revised implementation to remove a genre to a book and verify functionality in Book domain
    // change dto - request is wrong
    @DeleteMapping("/{bookId}/genres")
    public BaseResponse<Void> removeGenreFromBook(
            @PathVariable("bookId") String bookId,
            @Valid @RequestBody BookAddGenreRequest request) {
        bookService.removeGenreFromBook(bookId, request);
        return BaseResponse.SUCCESS;
    }


}
