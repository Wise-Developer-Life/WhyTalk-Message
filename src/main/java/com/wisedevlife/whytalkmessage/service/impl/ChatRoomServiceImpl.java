package com.wisedevlife.whytalkmessage.service.impl;

import com.wisedevlife.whytalkmessage.common.exception.chatroom.ChatRoomExistedException;
import com.wisedevlife.whytalkmessage.common.exception.chatroom.ChatRoomNotFoundException;
import com.wisedevlife.whytalkmessage.entity.ChatRoom;
import com.wisedevlife.whytalkmessage.repository.ChatRoomRepository;
import com.wisedevlife.whytalkmessage.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom createOneToOneChatRoom(String user1, String user2) {
        Optional<ChatRoom> existedChatRoom = chatRoomRepository.findOneToOneChatRoom(user1, user2);
        if (existedChatRoom.isPresent()) {
            throw new ChatRoomExistedException(existedChatRoom.get());
        }

        List<String> users = List.of(user1, user2);
        ChatRoom chatRoom = ChatRoom
                .builder()
                .userIds(users)
                .build();
        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public List<ChatRoom> getChatRooms(String userId, int offset, int limit) {
        //TODO: check if user exists in future after user service got implemented
        return chatRoomRepository.findLatestChatRoomsOfUserIdLimitedToWithOffset(userId, offset, limit);
    }

    @Override
    public int getNumberOfChatRoomsByUserId(String userId) {
        Integer count = chatRoomRepository.countChatRoomsByUserIdsContains(userId);
        return count != null ? count : 0;
    }

    @Override
    public void deleteChatRoom(String roomId) {
        if (!chatRoomRepository.existsById(new ObjectId(roomId))) {
            throw new ChatRoomNotFoundException(roomId);
        }

        chatRoomRepository.deleteById(new ObjectId(roomId));
    }
}
