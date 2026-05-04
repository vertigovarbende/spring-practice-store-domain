package com.deveyk.bookstore.category.service.command;

import com.deveyk.bookstore.category.model.enums.CategoryStatus;
import com.deveyk.bookstore.common.model.Filtering;
import com.deveyk.bookstore.common.model.mapper.BsSpecification;
import com.deveyk.bookstore.common.service.command.BsPaginationCommand;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

@Getter
@Setter
@SuperBuilder
public class CategoryListCommand extends BsPaginationCommand implements BsSpecification {

    private CategoryFilter filter;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class CategoryFilter implements Filtering {

        private String name;
        private String description;
        private CategoryStatus status;

    }

    public <C> Specification<C> toSpecification(Class<C> cClass) {

        if (this.filter == null) {
            return Specification.allOf();
        }

        Specification<C> spec = Specification.allOf();;

        if (StringUtils.hasText(this.filter.getName())) {
            spec = spec.and(nameContains(this.filter.getName()));
        }

        if (StringUtils.hasText(this.filter.getDescription())) {
            spec = spec.and(descriptionContains(this.filter.getDescription()));
        }

        if (this.filter.getStatus() != null) {
            spec = spec.and(hasStatus(this.filter.getStatus()));
        }

        return spec;
    }

    private <C> Specification<C> nameContains(String name) {
        return (root, query, cb) ->
                cb.like(
                        cb.lower(cb.coalesce(root.get("name"), "")),
                        "%" + name.toLowerCase() + "%"
                );
    }

    private <C> Specification<C> descriptionContains(String description) {
        return (root, query, cb) ->
                cb.like(
                        cb.lower(cb.coalesce(root.get("description"), "")),
                        "%" + description.toLowerCase() + "%"
                );
    }

    private <C> Specification<C> hasStatus(CategoryStatus status) {
        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }

}
