package com.deveyk.bookstore.category.service.domain;

import com.deveyk.bookstore.category.exception.CategoryStatusAlreadyChangedException;
import com.deveyk.bookstore.category.exception.CategoryStatusNotSuitableForOperationException;
import com.deveyk.bookstore.category.model.enums.CategoryStatus;
import lombok.Builder;
import lombok.Getter;

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
                .status(CategoryStatus.ACTIVE)
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

    public void activate() {
        if (this.status == CategoryStatus.ACTIVE) {
            throw new CategoryStatusAlreadyChangedException("Category is already ACTIVE");
        }

        if (this.status == CategoryStatus.DELETED) {
            throw new CategoryStatusNotSuitableForOperationException("Deleted category cannot be activated");
        }

        this.status = CategoryStatus.ACTIVE;
    }

    public void deactivate() {
        if (this.status == CategoryStatus.INACTIVE) {
            throw new CategoryStatusAlreadyChangedException("Category is already INACTIVE");
        }

        if (this.status == CategoryStatus.DELETED) {
            throw new CategoryStatusNotSuitableForOperationException("Deleted category cannot be deactivated");
        }

        this.status = CategoryStatus.INACTIVE;
    }

    public void markAsDeleted() {
        if (this.status == CategoryStatus.DELETED) {
            throw new CategoryStatusAlreadyChangedException("Category is already DELETED");
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
