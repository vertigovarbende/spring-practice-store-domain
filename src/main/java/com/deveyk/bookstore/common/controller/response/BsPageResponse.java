package com.deveyk.bookstore.common.controller.response;

import com.deveyk.bookstore.common.model.BsPage;
import com.deveyk.bookstore.common.model.Filtering;
import com.deveyk.bookstore.common.model.Sorting;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BsPageResponse<R> {

    private List<R> content;

    private int pageNumber;

    private int pageSize;

    private int totalPageCount;

    private Long totalElementCount;

    private Sorting sortedBy;

    private Filtering filteredBy;

    @SuppressWarnings("This method is used by Spring in the background")
    public static class BsPageResponseBuilder<R> {

        private BsPageResponseBuilder() {
        }

        public <Z> BsPageResponseBuilder<R> page(final BsPage<Z> page) {
            this.pageNumber = page.getPageNumber();
            this.pageSize = page.getPageSize();
            this.totalPageCount = page.getTotalPageCount();
            this.totalElementCount = page.getTotalElementCount();
            this.sortedBy = page.getSortedBy();
            this.filteredBy = page.getFilteredBy();
            return this;
        }

    }

}
