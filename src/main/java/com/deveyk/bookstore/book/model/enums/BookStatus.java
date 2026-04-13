package com.deveyk.bookstore.book.model.enums;

public enum BookStatus {

    AVAILABLE {
        @Override
        public boolean canTransitionTo(BookStatus targetStatus) {
            return targetStatus == UNAVAILABLE;
        }
    },

    UNAVAILABLE {
        @Override
        public boolean canTransitionTo(BookStatus targetStatus) {
            return targetStatus == AVAILABLE;
        }
    },

    DELETED {
        @Override
        public boolean canTransitionTo(BookStatus targetStatus) {
            return false;
        }
    };

    public abstract boolean canTransitionTo(BookStatus targetStatus);

    // STATE MACHINE - i might implement this later
    /*
    private final Set<BookStatus> allowedTransitions;

    BookStatus(Set<BookStatus> allowedTransitions) {
        this.allowedTransitions = allowedTransitions;
    }

    public boolean canTransitionTo(BookStatus target) {
        return allowedTransitions.contains(target);
    }
     */
}
