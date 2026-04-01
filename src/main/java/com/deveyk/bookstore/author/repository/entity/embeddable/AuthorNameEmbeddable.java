package com.deveyk.bookstore.author.repository.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorNameEmbeddable {

    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;

    @Column(name = "MIDDLE_NAME", length = 100)
    private String middleName;

    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;

    @Column(name = "PEN_NAME", length = 200)
    private String penName;

    @Column(name = "TITLE", length = 50)
    private String title; // Dr., Prof., etc.

    @Column(name = "SUFFIX", length = 20)
    private String suffix; // Jr., Sr., III

    // Helper method
    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (title != null) sb.append(title).append(" ");
        sb.append(firstName);
        if (middleName != null) sb.append(" ").append(middleName);
        sb.append(" ").append(lastName);
        if (suffix != null) sb.append(" ").append(suffix);
        return sb.toString().trim();
    }

    public String getDisplayName() {
        return penName != null && !penName.trim().isEmpty() ? penName : getFullName();
    }
}