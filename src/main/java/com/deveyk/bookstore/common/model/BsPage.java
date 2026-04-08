package com.deveyk.bookstore.common.model;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class BsPage<T> {

    private List<T> content;

    private int pageNumber;

    private int pageSize;

    private int totalPageCount;

    private long totalElementCount;

    private Sorting sortedBy;

    private Filtering filteredBy;

    public static class BsPageBuilder<T> {

        private BsPageBuilder() {

        }

        public <Z> BsPageBuilder<T> page(Page<Z> page) {

            return this
                    .pageNumber(page.getNumber() + 1)
                    .pageSize(page.getContent().size())
                    .totalPageCount(page.getTotalPages())
                    .totalElementCount(page.getTotalElements())
                    .sortedBy(Sorting.toSort(page.getSort()));

        }

    }


}
