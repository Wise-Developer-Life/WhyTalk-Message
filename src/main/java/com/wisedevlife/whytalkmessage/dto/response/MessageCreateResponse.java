package com.wisedevlife.whytalkmessage.dto.response;

public record MessageCreateResponse(String content, String fromUser, String toUser, String chatRoomId) {}
