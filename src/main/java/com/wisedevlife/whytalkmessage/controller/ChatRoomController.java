package com.wisedevlife.whytalkmessage.controller;

import com.wisedevlife.whytalkmessage.common.helper.ResponseHandler;
import com.wisedevlife.whytalkmessage.dto.request.OneToOneChatRoomCreationRequest;
import com.wisedevlife.whytalkmessage.dto.response.ChatRoomResponse;
import com.wisedevlife.whytalkmessage.dto.response.ReturnResponse;
import com.wisedevlife.whytalkmessage.entity.ChatRoom;
import com.wisedevlife.whytalkmessage.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping
    public ResponseEntity<ReturnResponse<String>> helloChatRoom() {
        return ResponseHandler.success("Hello ChatRoom");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ReturnResponse<Page<ChatRoomResponse>>> getOneToOneChatRoomsByUser(@PathVariable String userId, @RequestParam int offset, @RequestParam int limit) {
        Page<ChatRoom> chatRooms = chatRoomService.getChatRooms(userId, offset, limit);
        Page<ChatRoomResponse> response = chatRooms.map(ChatRoomResponse::toChatRoomResponse);
        return ResponseHandler.success(response);
    }

    @PostMapping
    public ResponseEntity<ReturnResponse<ChatRoomResponse>> createOneToOneChatRoom(@RequestBody OneToOneChatRoomCreationRequest chatRoomCreationRequest) {
        List<String> users = List.of(chatRoomCreationRequest.user1(), chatRoomCreationRequest.user2());
        ChatRoom createdRoom = chatRoomService.createOneToOneChatRoom(users.get(0), users.get(1));
        ChatRoomResponse response = ChatRoomResponse.toChatRoomResponse(createdRoom);
        return ResponseHandler.success(response);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<ReturnResponse<String>> deleteChatRoom(@PathVariable String roomId) {
        chatRoomService.deleteChatRoom(roomId);
        return ResponseHandler.success("Deleted");
    }

}
