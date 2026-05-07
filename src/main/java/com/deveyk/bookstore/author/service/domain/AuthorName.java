package com.deveyk.bookstore.author.service.domain;

import lombok.*;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Builder
public class AuthorName {

    private String firstName;
    private String middleName;
    private String lastName;
    private String penName;
    private String title; // Dr., Prof., etc.
    private String suffix; // Jr., Sr., III

    public String displayName() {
        if (hasText(this.penName)) {
            return this.penName.trim();
        }

        return fullName();
    }

    public String fullName() {
        return Stream.of(title, firstName, middleName, lastName, suffix)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .collect(Collectors.joining(" "));
    }

    public boolean hasRequiredLegalName() {
        return hasText(this.firstName) && hasText(this.lastName);
    }

    public boolean hasPenName() {
        return hasText(this.penName);
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

}
