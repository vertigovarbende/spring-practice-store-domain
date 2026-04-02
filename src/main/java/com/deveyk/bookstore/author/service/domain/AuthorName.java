package com.deveyk.bookstore.author.service.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorName {

    private String firstName;
    private String middleName;
    private String lastName;
    private String penName;
    private String title; // Dr., Prof., etc.
    private String suffix; // Jr., Sr., III

}
