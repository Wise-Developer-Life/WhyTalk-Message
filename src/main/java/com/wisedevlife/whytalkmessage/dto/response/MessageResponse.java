package com.wisedevlife.whytalkmessage.dto.response;

import com.wisedevlife.whytalkmessage.entity.Message;
import java.time.Instant;

public record MessageResponse(
        String content,
        String fromUser,
        String toUser,
        String chatRoomId,
        Long sendDateTimestamp,
        Long readDateTimestamp) {
    public static MessageResponse toMessageResponse(Message message) {
        long sendTimeStamps = message.getSendDateTime().getEpochSecond();
        Instant readTime = message.getReadDateTime();
        Long readTimestamp = readTime != null ? readTime.getEpochSecond() : null;
        return new MessageResponse(
                message.getContent(),
                message.getFromUser(),
                message.getToUser(),
                message.getChatRoomId(),
                sendTimeStamps,
                readTimestamp);
    }
}
