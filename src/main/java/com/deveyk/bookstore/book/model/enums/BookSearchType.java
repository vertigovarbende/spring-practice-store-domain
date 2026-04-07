package com.deveyk.bookstore.book.model.enums;

import java.util.Arrays;

public enum BookSearchType {

    TITLE,
    AUTHOR,
    GENRE,
    CATEGORY;

    // should i create custom exception instead of IllegalArgumentException?
    public static BookSearchType from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Search type cannot be null or blank");
        }

        return Arrays.stream(values())
                .filter(type -> type.name().equalsIgnoreCase(value.trim()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid search type: " + value)
                );
    }


}
