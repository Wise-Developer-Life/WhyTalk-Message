package com.wisedevlife.whytalkmessage.service;

import com.wisedevlife.whytalkmessage.entity.ChatRoom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChatRoomService {
    ChatRoom createOneToOneChatRoom(String user1, String user2);
    List<ChatRoom> getChatRooms(String userId, int offset, int limit);
    int getNumberOfChatRoomsByUserId(String userId);
    void deleteChatRoom(String roomId);
}
