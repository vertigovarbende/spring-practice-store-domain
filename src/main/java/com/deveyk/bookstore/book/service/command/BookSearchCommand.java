package com.deveyk.bookstore.book.service.command;

import com.deveyk.bookstore.common.model.Filtering;
import com.deveyk.bookstore.common.model.mapper.BsSpecification;
import com.deveyk.bookstore.common.service.command.BsPaginationCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.Specification;

@Getter
@SuperBuilder
public class BookSearchCommand extends BsPaginationCommand implements BsSpecification {

    private BookSearchFilter filter;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class BookSearchFilter implements Filtering {
        private String searchTerm;
        private String searchType;
    }

    @Override
    public <C> Specification<C> toSpecification(Class<C> clazz) {
        return null;
    }

}
