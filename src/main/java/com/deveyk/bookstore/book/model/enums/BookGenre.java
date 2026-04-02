package com.deveyk.bookstore.book.model.enums;

import java.util.Arrays;

public enum BookGenre {

    FANTASY,
    SCIENCE_FICTION,
    ROMANCE,
    THRILLER,
    HORROR,
    MYSTERY,
    DRAMA,
    HISTORY;

    // should i create custom exception instead of IllegalArgumentException?
    public static BookGenre from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Genre cannot be null or blank");
        }

        String normalizedValue = value.trim()
                .toUpperCase()
                .replace(" ", "_");

        return Arrays.stream(BookGenre.values())
                .filter(bookGenre -> bookGenre.name().equals(normalizedValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid genre: " + value));
    }
}
