package com.wisedevlife.whytalkmessage.service.implement;

import com.wisedevlife.whytalkmessage.common.exception.chatroom.ChatRoomExistedException;
import com.wisedevlife.whytalkmessage.common.exception.chatroom.ChatRoomNotFoundException;
import com.wisedevlife.whytalkmessage.entity.ChatRoom;
import com.wisedevlife.whytalkmessage.entity.Message;
import com.wisedevlife.whytalkmessage.model.OneToOneChatRoomModel;
import com.wisedevlife.whytalkmessage.model.UserModel;
import com.wisedevlife.whytalkmessage.repository.ChatRoomRepository;
import com.wisedevlife.whytalkmessage.service.ChatRoomService;
import com.wisedevlife.whytalkmessage.service.MessageService;
import com.wisedevlife.whytalkmessage.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final MessageService messageService;
    private final UserService userService;

    @Override
    public ChatRoom createOneToOneChatRoom(String user1, String user2) {
        Optional<ChatRoom> existedChatRoom = chatRoomRepository.findOneToOneChatRoom(user1, user2);
        if (existedChatRoom.isPresent()) {
            throw new ChatRoomExistedException(existedChatRoom.get());
        }

        List<String> users = List.of(user1, user2);
        ChatRoom chatRoom = ChatRoom.builder().userIds(users).build();

        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public List<OneToOneChatRoomModel> getChatRooms(String userId, int offset, int limit) {
        Optional<UserModel> user = userService.getUserByUserId(userId);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        List<ChatRoom> roomsOfUser =
                chatRoomRepository.findLatestChatRoomsOfUserIdLimitedToWithOffset(
                        userId, offset, limit);
        return roomsOfUser.stream().map(room -> toOneToOneChatRoomModel(room, userId)).toList();
    }

    private OneToOneChatRoomModel toOneToOneChatRoomModel(ChatRoom chatRoom, String userId) {
        if (chatRoom.getUserIds().size() != 2) {
            throw new RuntimeException("Chat room is not one-to-one chat room");
        }

        Optional<String> opCurrentUserId =
                chatRoom.getUserIds().stream().filter(id -> id.equals(userId)).findFirst();

        if (opCurrentUserId.isEmpty()) {
            throw new RuntimeException("User is not in chat room");
        }

        chatRoom.getUserIds().remove(opCurrentUserId.get());
        String otherUserId = chatRoom.getUserIds().get(0);

        Optional<UserModel> otherUser = userService.getUserByUserId(otherUserId);
        if (otherUser.isEmpty()) {
            log.error("User not found");
            return null;
        }

        Message lastMessage =
                messageService.getLastMessageFromChatRoom(chatRoom.getId().toString());

        return OneToOneChatRoomModel.builder()
                .roomId(chatRoom.getId().toString())
                .lastMessage(lastMessage)
                .otherUser(otherUser.get())
                .build();
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
