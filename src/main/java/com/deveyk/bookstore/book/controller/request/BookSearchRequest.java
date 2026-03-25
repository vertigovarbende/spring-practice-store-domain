package com.deveyk.bookstore.book.controller.request;

public record BookSearchRequest(
    String searchTerm,
    String searchType
) {


}
