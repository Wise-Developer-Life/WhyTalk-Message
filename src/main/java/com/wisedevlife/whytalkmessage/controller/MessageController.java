package com.wisedevlife.whytalkmessage.controller;

import com.wisedevlife.whytalkmessage.common.helper.ResponseHandler;
import com.wisedevlife.whytalkmessage.dto.request.MessageRequest;
import com.wisedevlife.whytalkmessage.dto.response.MessageCreateResponse;
import com.wisedevlife.whytalkmessage.dto.response.MessageResponse;
import com.wisedevlife.whytalkmessage.dto.response.ReturnResponse;
import com.wisedevlife.whytalkmessage.entity.Message;
import com.wisedevlife.whytalkmessage.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@Tag(name="Message Service API")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @Operation(summary = "Get paged messages from a chat room and sorted by created time in descending order")
    @GetMapping("/{chatRoomId}")
    public ResponseEntity<ReturnResponse<Page<MessageResponse>>> getAllMessages(
            @PathVariable String chatRoomId,
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit
    ) {
        // TODO: do not directly expose response which use Spring Data Object Page
        Page<Message> messages = messageService.getPagedMessagesByChatRoomId(chatRoomId, offset, limit);
        // TODO: try to use modelMapper to convert between entity and dto
        Page<MessageResponse> response = messages.map(MessageResponse::toMessageResponse);
        return ResponseHandler.success(response);
    }

    @PostMapping
    @Operation(summary = "Create a message by api, for test only", deprecated = true)
    public ResponseEntity<ReturnResponse<MessageCreateResponse>> postMessage(@RequestBody MessageRequest messageRequest) {
        messageService.saveMessage(messageRequest);
        MessageCreateResponse response =
                new MessageCreateResponse(
                        messageRequest.content(),
                        messageRequest.fromUser(),
                        messageRequest.toUser(),
                        messageRequest.chatRoomId());
        return ResponseHandler.success(response);
    }
}
