package com.deveyk.bookstore.category.service.domain;

import com.deveyk.bookstore.category.exception.CategoryStatusAlreadyChangedException;
import com.deveyk.bookstore.category.exception.CategoryStatusNotSuitableForOperationException;
import com.deveyk.bookstore.category.model.enums.CategoryStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
public class Category {

    private UUID id;
    private String name;
    private String description;
    private CategoryStatus status;

    public static Category create(String name, String description) {
        return Category.builder()
                .id(UUID.randomUUID())
                .name(name)
                .description(description)
                .status(CategoryStatus.INACTIVE)
                .build();
    }

    public boolean isActiveForBookAssignment() {
        return this.status == CategoryStatus.ACTIVE;
    }

    public void updateDetails(String name, String description) {
        validateUpdatable();

        this.name = name;
        this.description = description;
    }

    private void validateUpdatable() {
        if (this.status == CategoryStatus.DELETED) {
            throw new CategoryStatusNotSuitableForOperationException(
                    "Deleted category cannot be updated"
            );
        }
    }

    // Status methods
    /*
    - only INACTIVE categories can be marked as DELETED
    - only DELETED categories can be restored
    - category status cannot be changed from ACTIVE to DELETED
    - category status cannot be changed from DELETED to ACTIVE
     */
    public void activate() {
        changeStatus(CategoryStatus.ACTIVE);
    }

    public void deactivate() {
        changeStatus(CategoryStatus.INACTIVE);
    }

    private void changeStatus(CategoryStatus targetStatus) {
        Objects.requireNonNull(targetStatus, "Target status cannot be null");

        if (this.status == targetStatus) {
            throw new CategoryStatusAlreadyChangedException(
                    "Category is already in " + targetStatus.name() + " status"
            );
        }

        if (!this.status.canTransitionTo(targetStatus)) {
            throw new CategoryStatusNotSuitableForOperationException(
                    "Category status cannot be changed from " + this.status.name() + " to " + targetStatus.name()
            );
        }

        this.status = targetStatus;
    }

    public void markAsDeleted() {
        if (this.status == CategoryStatus.DELETED) {
            throw new CategoryStatusAlreadyChangedException("Category is already DELETED");
        }

        if (this.status != CategoryStatus.INACTIVE) {
            throw new CategoryStatusNotSuitableForOperationException(
                    "Only INACTIVE categories can be marked as DELETED"
            );
        }

        this.status = CategoryStatus.DELETED;
    }

    public void restore() {
        if (this.status != CategoryStatus.DELETED) {
            throw new CategoryStatusNotSuitableForOperationException(
                    "Only DELETED categories can be restored"
            );
        }

        this.status = CategoryStatus.INACTIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return this.id != null && this.id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
