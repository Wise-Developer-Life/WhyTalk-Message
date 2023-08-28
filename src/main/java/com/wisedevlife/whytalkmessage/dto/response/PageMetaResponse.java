package com.wisedevlife.whytalkmessage.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Page Meta Response")
public record PageMetaResponse(
        @Schema(description = "Current Page", example = "0")
        int currentPage,
        @Schema(description = "Page Size", example = "10")
        int pageSize,
        @Schema(description = "Total Page", example = "2")
        int totalPage,

        @Schema(description = "Total Elements", example = "200")
        long totalElements,
        @Schema(description = "Is current page the last page", example = "true")
        boolean isLast
) {
}
