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
            return BsPageResponse.<R>builder()
                    .pageNumber(page.getPageNumber())
                    .pageSize(page.getPageSize())
                    .totalPageCount(page.getTotalPages())
                    .totalElementCount(page.getTotalElements())
                    .sortedBy(page.getSortedBy())
                    .filteredBy(page.getFilteredBy());
        }

    }

}
