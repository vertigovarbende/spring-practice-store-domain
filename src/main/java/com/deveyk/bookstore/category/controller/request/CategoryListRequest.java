package com.deveyk.bookstore.category.controller.request;

import com.deveyk.bookstore.category.model.enums.CategoryStatus;
import com.deveyk.bookstore.common.controller.request.BsPaginationRequest;
import com.deveyk.bookstore.common.model.Filtering;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@SuperBuilder
@NoArgsConstructor
public class CategoryListRequest extends BsPaginationRequest {

    private CategoryFilter filter;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CategoryFilter implements Filtering {
        private String name;
        private String description;
        private CategoryStatus status;
    }

    @Override
    public boolean isOrderPropertyAccepted() {
       final Set<String> acceptedProperties = Set.of("name", "status");
        return this.isPropertyAccepted(acceptedProperties);
    }

}
