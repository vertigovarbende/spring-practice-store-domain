package com.deveyk.bookstore.common.service.command;

import com.deveyk.bookstore.common.model.Pagination;
import com.deveyk.bookstore.common.model.Sorting;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@SuperBuilder
public abstract class BsPaginationCommand {

    private Pagination pagination;

    private Sorting sorting;

    public Pageable toPageable() {
        if (this.sorting != null)
            return PageRequest.of(
                    this.pagination.getPageNumber() - 1,
                    this.pagination.getPageSize(),
                    Sort.by(Sort.Order.by(this.sorting.getProperty()).with(this.sorting.getDirection()))
            );
        else
            return PageRequest.of(
                    this.pagination.getPageNumber() - 1,
                    this.pagination.getPageSize()
            );
    }

}
