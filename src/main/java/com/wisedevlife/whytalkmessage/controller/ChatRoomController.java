package com.wisedevlife.whytalkmessage.controller;

import com.wisedevlife.whytalkmessage.common.helper.ResponseHandler;
import com.wisedevlife.whytalkmessage.dto.request.OneToOneChatRoomCreationRequest;
import com.wisedevlife.whytalkmessage.dto.response.ChatRoomResponse;
import com.wisedevlife.whytalkmessage.dto.response.ReturnResponse;
import com.wisedevlife.whytalkmessage.dto.response.ScrollResponse;
import com.wisedevlife.whytalkmessage.entity.ChatRoom;
import com.wisedevlife.whytalkmessage.service.ChatRoomService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatroom")
@Tag(name = "Chat Room Service API")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/{userId}")
    @Operation(
            summary =
                    "Get paged chat rooms of a user sorted by last message sent time in descending"
                            + " order")
    public ResponseEntity<ReturnResponse<ScrollResponse<ChatRoomResponse>>>
            getOneToOneChatRoomsByUser(
                    @PathVariable String userId,
                    @RequestParam int offset,
                    @RequestParam int limit) {
        List<ChatRoom> chatRooms = chatRoomService.getChatRooms(userId, offset, limit);
        List<ChatRoomResponse> data =
                chatRooms.stream().map(ChatRoomResponse::toChatRoomResponse).toList();
        int chatRoomsCount = chatRoomService.getNumberOfChatRoomsByUserId(userId);
        ScrollResponse<ChatRoomResponse> chatRoomScrollResponse =
                ScrollResponse.of(data, offset, limit, chatRoomsCount);
        return ResponseHandler.success(chatRoomScrollResponse);
    }

    @PostMapping
    @Operation(summary = "Create a one-to-one chat room")
    public ResponseEntity<ReturnResponse<ChatRoomResponse>> createOneToOneChatRoom(
            @RequestBody OneToOneChatRoomCreationRequest chatRoomCreationRequest) {
        List<String> users =
                List.of(chatRoomCreationRequest.user1(), chatRoomCreationRequest.user2());
        ChatRoom createdRoom = chatRoomService.createOneToOneChatRoom(users.get(0), users.get(1));
        ChatRoomResponse response = ChatRoomResponse.toChatRoomResponse(createdRoom);
        return ResponseHandler.success(response);
    }

    // TODO: fix chat room delete
    @DeleteMapping("/{roomId}")
    @Hidden
    @Operation(summary = "Delete a chat room with given room id")
    public ResponseEntity<ReturnResponse<String>> deleteChatRoom(@PathVariable String roomId) {
        chatRoomService.deleteChatRoom(roomId);
        return ResponseHandler.success("Deleted");
    }
}
