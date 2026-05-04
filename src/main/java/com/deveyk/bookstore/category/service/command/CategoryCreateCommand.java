package com.deveyk.bookstore.category.service.command;

public record CategoryCreateCommand(
        String name,
        String description
) {
}
