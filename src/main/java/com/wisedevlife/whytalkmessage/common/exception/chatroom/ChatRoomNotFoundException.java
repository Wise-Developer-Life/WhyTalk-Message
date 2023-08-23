package com.wisedevlife.whytalkmessage.common.exception.chatroom;

public class ChatRoomNotFoundException extends RuntimeException {
    public ChatRoomNotFoundException(String roomId) {
        super(String.format("ChatRoom not found. (roomId: %s)", roomId));
    }
}
