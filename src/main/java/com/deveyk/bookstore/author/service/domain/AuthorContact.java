package com.deveyk.bookstore.author.service.domain;

import com.deveyk.bookstore.author.model.enums.ContactMethod;
import lombok.*;

@Getter
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
    @Builder.Default
    private Boolean isAvailableForEvents = false;

    public boolean hasEmail() {
        return hasText(this.email);
    }

    public boolean hasPhone() {
        return hasText(this.phone);
    }

    public boolean hasAgentContact() {
        return hasText(this.agentContact);
    }

    public boolean hasSocialMediaContact() {
        return hasText(this.twitterHandle)
                || hasText(this.instagramHandle)
                || hasText(this.linkedinUrl);
    }

    public boolean canBeContactedByPreferredMethod() {
        return this.preferredContactMethod != null
                && this.preferredContactMethod.isAvailableFor(this);
    }

    public boolean isAvailableForEvents() {
        return Boolean.TRUE.equals(this.isAvailableForEvents);
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

}
