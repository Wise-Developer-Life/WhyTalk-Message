package com.wisedevlife.whytalkmessage.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(description = "Pagination Response of Data")
public record PageResponse<T>(
        @Schema(description = "Page Meta Response")
        PageMetaResponse pageMeta,
        @Schema(description = "Data")
        List<T> data
) {
    public static <T> PageResponse<T> of(Page<T> pageDTO) {
        PageMetaResponse pageMeta = PageMetaResponse.builder()
                .totalPage(pageDTO.getTotalPages())
                .totalElements(pageDTO.getTotalElements())
                .currentPage(pageDTO.getNumber())
                .pageSize(pageDTO.getSize())
                .isLast(pageDTO.isLast())
                .build();
        return new PageResponse<>(pageMeta, pageDTO.getContent());
    }
}
