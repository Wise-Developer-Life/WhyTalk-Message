package com.wisedevlife.whytalkmessage.controller;

import com.wisedevlife.whytalkmessage.dto.MessageCreateResponse;
import com.wisedevlife.whytalkmessage.dto.MessageRequest;
import com.wisedevlife.whytalkmessage.dto.MessageResponse;
import com.wisedevlife.whytalkmessage.entity.Message;
import com.wisedevlife.whytalkmessage.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<MessageResponse>> getAllMessages() {
        List<Message> messages = messageService.getMessages();
        List<MessageResponse> response =
                messages.stream().map(MessageResponse::toMessageResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public String getMessage(@PathVariable int id) {
        return "Hello World";
    }

    @PostMapping
    public ResponseEntity<MessageCreateResponse> postMessage(
            @RequestBody MessageRequest messageRequest) {
        messageService.saveMessage(messageRequest);
        MessageCreateResponse response =
                new MessageCreateResponse(
                        messageRequest.content(),
                        messageRequest.fromUser(),
                        messageRequest.toUser());
        return ResponseEntity.ok(response);
    }
}
