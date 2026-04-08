package com.deveyk.bookstore.book.service.command;

import com.deveyk.bookstore.book.model.enums.BookSearchType;
import com.deveyk.bookstore.book.model.enums.BookStatus;
import com.deveyk.bookstore.common.model.Filtering;
import com.deveyk.bookstore.common.model.mapper.BsSpecification;
import com.deveyk.bookstore.common.service.command.BsPaginationCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Getter
@SuperBuilder
public class BookSearchCommand extends BsPaginationCommand implements BsSpecification {

    private BookSearchFilter filter;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class BookSearchFilter implements Filtering {
        private String searchTerm;
        private BookSearchType searchType;

        // category, price range, status
        private BookStatus status;
    }

    @Override
    public <C> Specification<C> toSpecification(Class<C> clazz) {
        if (this.filter == null) {
            return Specification.allOf();
        }

        Specification<C> spec = Specification.allOf();

        if (this.filter.getStatus() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("status"), this.filter.getStatus()));
        }

        return spec;
    }

}
