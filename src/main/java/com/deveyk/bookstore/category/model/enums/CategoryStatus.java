package com.deveyk.bookstore.category.model.enums;

public enum CategoryStatus {

    ACTIVE {
        @Override
        public boolean canTransitionTo(CategoryStatus targetStatus) {
            return targetStatus == INACTIVE;
        }
    },

    INACTIVE {
        @Override
        public boolean canTransitionTo(CategoryStatus targetStatus) {
            return targetStatus == ACTIVE;
        }
    },

    DELETED {
        @Override
        public boolean canTransitionTo(CategoryStatus targetStatus) {
            return false;
        }
    };

    public abstract boolean canTransitionTo(CategoryStatus targetStatus);
}
