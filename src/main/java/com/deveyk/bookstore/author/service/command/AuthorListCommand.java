package com.deveyk.bookstore.author.service.command;

import com.deveyk.bookstore.author.model.enums.AuthorStatus;
import com.deveyk.bookstore.author.service.domain.AuthorName;
import com.deveyk.bookstore.common.model.Filtering;
import com.deveyk.bookstore.common.model.mapper.BsSpecification;
import com.deveyk.bookstore.common.service.command.BsPaginationCommand;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
public class AuthorListCommand extends BsPaginationCommand implements BsSpecification {

    private AuthorFilter filter;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AuthorFilter implements Filtering {

        private AuthorName name;
        private AuthorStatus status;

    }

    public <C> Specification<C> toSpecification(Class<C> cClass) {

        if (this.filter == null) {
            return Specification.allOf();
        }

        Specification<C> spec = Specification.allOf();

        if (this.filter.getName() != null) {
            spec = spec.and(nameContains(this.filter.getName()));
        }

        if (this.filter.getStatus() != null) {
            spec = spec.and(hasStatus(this.filter.getStatus()));
        }

        return spec;

    }

    private <C> Specification<C> nameContains(AuthorName name) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(name.getFirstName())) {
                predicates.add(cb.like(
                        cb.lower(cb.coalesce(root.get("name").get("firstName"), "")),
                        "%" + name.getFirstName().toLowerCase() + "%"
                ));
            }

            if (StringUtils.hasText(name.getMiddleName())) {
                predicates.add(cb.like(
                        cb.lower(cb.coalesce(root.get("name").get("middleName"), "")),
                        "%" + name.getMiddleName().toLowerCase() + "%"
                ));
            }

            if (StringUtils.hasText(name.getLastName())) {
                predicates.add(cb.like(
                        cb.lower(cb.coalesce(root.get("name").get("lastName"), "")),
                        "%" + name.getLastName().toLowerCase() + "%"
                ));
            }

            if (StringUtils.hasText(name.getPenName())) {
                predicates.add(cb.like(
                        cb.lower(cb.coalesce(root.get("name").get("penName"), "")),
                        "%" + name.getPenName().toLowerCase() + "%"
                ));
            }

            if (predicates.isEmpty()) {
                return cb.conjunction();
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private <C>Specification<C> hasStatus(AuthorStatus status) {
        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }


}
