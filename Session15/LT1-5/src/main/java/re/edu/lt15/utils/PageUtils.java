package re.edu.lt15.utils;

import org.springframework.data.domain.Page;
import re.edu.lt15.dto.response.PageResponse;

public class PageUtils {
    public static <T> PageResponse<T> toPageResponse(Page<T> page) {
        return PageResponse.<T>builder()
                .items(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isLast(page.isLast())
                .build();
    }
}