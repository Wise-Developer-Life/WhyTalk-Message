package com.wisedevlife.whytalkmessage.dto;

public record MessageCreateResponse(
        String content,
        String fromUser,
        String toUser
) {
}
