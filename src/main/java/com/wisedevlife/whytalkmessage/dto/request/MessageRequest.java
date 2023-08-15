package com.wisedevlife.whytalkmessage.dto.request;

import com.wisedevlife.whytalkmessage.entity.Message;
import java.time.Instant;

public record MessageRequest(
        String content, String fromUser, String toUser, String roomId, long sendDateTimestamp) {
    public Message toEntity() {
        Instant sendDateTime = Instant.ofEpochMilli(sendDateTimestamp());

        return Message.builder()
                .content(this.content())
                .fromUser(this.fromUser())
                .toUser(this.toUser())
                .sendDateTime(sendDateTime)
                .build();
    }
}
