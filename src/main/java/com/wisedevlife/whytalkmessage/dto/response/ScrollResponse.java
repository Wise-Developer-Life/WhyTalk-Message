package com.wisedevlife.whytalkmessage.dto.response;

import lombok.Builder;

import java.util.List;

public record ScrollResponse<T>(
        long offset,
        int limit,
        int total,
        boolean isLast,
        List<T> data
) {
    public static <T> ScrollResponse<T> of(List<T> data, long currentOffset, int limit, int totalElements) {
        boolean isReachEnd = currentOffset + limit >= totalElements;
        return new ScrollResponse<>(currentOffset, limit, totalElements, isReachEnd, data);
    }
}
