package com.deveyk.bookstore.author.model.enums;

public enum AuthorStatus {

    ACTIVE {
        @Override
        public boolean canTransitionTo(AuthorStatus targetStatus) {
            return targetStatus == INACTIVE
                    || targetStatus == RETIRED
                    || targetStatus == SUSPENDED;
        }
    },

    INACTIVE {
        @Override
        public boolean canTransitionTo(AuthorStatus targetStatus) {
            return targetStatus == ACTIVE
                    || targetStatus == SUSPENDED;
        }
    },

    RETIRED {
        @Override
        public boolean canTransitionTo(AuthorStatus targetStatus) {
            return targetStatus == ACTIVE;
        }
    },

    SUSPENDED {
        @Override
        public boolean canTransitionTo(AuthorStatus targetStatus) {
            return targetStatus == ACTIVE
                    || targetStatus == INACTIVE;
        }
    },

    DECEASED {
        @Override
        public boolean canTransitionTo(AuthorStatus targetStatus) {
            return false;
        }
    };

    // STATE MACHINE ???????
    public abstract boolean canTransitionTo(AuthorStatus targetStatus);
}
