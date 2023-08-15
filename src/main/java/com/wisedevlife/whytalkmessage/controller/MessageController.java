package com.wisedevlife.whytalkmessage.controller;

import com.wisedevlife.whytalkmessage.common.helper.ResponseHandler;
import com.wisedevlife.whytalkmessage.dto.request.MessageRequest;
import com.wisedevlife.whytalkmessage.dto.response.MessageCreateResponse;
import com.wisedevlife.whytalkmessage.dto.response.MessageResponse;
import com.wisedevlife.whytalkmessage.dto.response.ReturnResponse;
import com.wisedevlife.whytalkmessage.entity.Message;
import com.wisedevlife.whytalkmessage.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<ReturnResponse<List<MessageResponse>>> getAllMessages() {
        List<Message> messages = messageService.getMessages();
        List<MessageResponse> response =
                messages.stream().map(MessageResponse::toMessageResponse).toList();
        return ResponseHandler.success(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnResponse<String>> getMessage(@PathVariable int id) {
        return ResponseHandler.success("Hello World");
    }

    @PostMapping
    public ResponseEntity<ReturnResponse<MessageCreateResponse>> postMessage(
            @RequestBody MessageRequest messageRequest) {
        messageService.saveMessage(messageRequest);
        MessageCreateResponse response =
                new MessageCreateResponse(
                        messageRequest.content(),
                        messageRequest.fromUser(),
                        messageRequest.toUser());
        return ResponseHandler.success(response);
    }
}
