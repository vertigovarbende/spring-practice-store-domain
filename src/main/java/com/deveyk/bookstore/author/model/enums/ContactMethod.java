package com.deveyk.bookstore.author.model.enums;

import com.deveyk.bookstore.author.service.domain.AuthorContact;

public enum ContactMethod {

    EMAIL {
        @Override
        public boolean isAvailableFor(AuthorContact contact) {
            return contact.hasEmail();
        }
    },

    PHONE {
        @Override
        public boolean isAvailableFor(AuthorContact contact) {
            return contact.hasPhone();
        }
    },

    AGENT {
        @Override
        public boolean isAvailableFor(AuthorContact contact) {
            return contact.hasAgentContact();
        }
    },

    SOCIAL_MEDIA {
        @Override
        public boolean isAvailableFor(AuthorContact contact) {
            return contact.hasSocialMediaContact();
        }
    },

    ALL {
        @Override
        public boolean isAvailableFor(AuthorContact contact) {
            return contact.hasEmail()
                    || contact.hasPhone()
                    || contact.hasAgentContact()
                    || contact.hasSocialMediaContact();
        }
    };

    // STATE MACHINE ???
    public abstract boolean isAvailableFor(AuthorContact contact);
}