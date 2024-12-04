package io.devandre.furtmates.shared.utils;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

@Data
@Accessors(chain = true)
public class PaginationInfo {

    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;

    public static PaginationInfo of(Page<?> page) {
        return new PaginationInfo()
                .setPageNumber(page.getNumber())
                .setPageSize(page.getSize())
                .setTotalElements(page.getTotalElements())
                .setTotalPages(page.getTotalPages())
                .setHasNext(page.hasNext())
                .setHasPrevious(page.hasPrevious());
    }
}
