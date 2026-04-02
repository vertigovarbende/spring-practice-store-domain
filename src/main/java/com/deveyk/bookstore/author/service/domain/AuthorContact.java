package com.deveyk.bookstore.author.service.domain;

import com.deveyk.bookstore.author.model.enums.ContactMethod;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthorContact {

    private final String email;
    private String phone;
    private String websiteUrl;
    private String twitterHandle;
    private String instagramHandle;
    private String linkedinUrl;
    private String agentContact;
    @Builder.Default
    private ContactMethod preferredContactMethod = ContactMethod.EMAIL;
    private Boolean isAvailableForEvents;

}
