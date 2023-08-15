package com.wisedevlife.whytalkmessage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MqMessagePayload<T>(
        @JsonProperty("pattern")
        String eventPattern,
        T data
) {
}
