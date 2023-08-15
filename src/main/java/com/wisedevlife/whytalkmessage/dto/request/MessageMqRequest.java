package com.wisedevlife.whytalkmessage.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public record MessageMqRequest(
        String content,
        String fromUser,
        String toUser,
        String roomId,
        @JsonProperty("createdAt") long sendDateTimestamp)
        implements Serializable {}
