package com.deveyk.bookstore.book.controller.impl;

import com.deveyk.bookstore.book.controller.mapper.BookToBookResponseMapper;
import com.deveyk.bookstore.book.controller.request.BookSearchRequest;
import com.deveyk.bookstore.book.controller.response.BookResponse;
import com.deveyk.bookstore.book.service.IBookService;
import com.deveyk.bookstore.book.service.impl.BookService;
import com.deveyk.bookstore.common.controller.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    private static final BookToBookResponseMapper bookToBookResponseMapper = BookToBookResponseMapper.INSTANCE;

    // GET - /api/v1/books/search - search() - first try for search strategy
    @GetMapping("/search")
    public BaseResponse<List<BookResponse>> search(@Valid @RequestBody BookSearchRequest request) {
        List<BookResponse> bookResponses = bookToBookResponseMapper.mapList(bookService.search(request));
        return BaseResponse.of(bookResponses);
    }


}
