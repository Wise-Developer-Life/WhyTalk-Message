package com.wisedevlife.whytalkmessage.common.exception.chatroom;

import com.wisedevlife.whytalkmessage.common.enums.ErrorCodeEnum;
import com.wisedevlife.whytalkmessage.common.helper.ResponseHandler;
import com.wisedevlife.whytalkmessage.dto.response.ReturnResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ChatRoomExceptionHandler {
    @ExceptionHandler(ChatRoomNotFoundException.class)
    public ResponseEntity<ReturnResponse<ErrorCodeEnum>> handleChatRoomNotFoundException(
            ChatRoomNotFoundException e) {
        log.error("Catching ChatRoomNotFoundException: {}.", e.toString());
        return ResponseHandler.badRequest(ErrorCodeEnum.CHATROOM_NOT_FOUND_ERROR);
    }

    @ExceptionHandler(ChatRoomExistedException.class)
    public ResponseEntity<ReturnResponse<ErrorCodeEnum>> handleChatRoomExistedException(
            ChatRoomExistedException e) {
        log.error("Catching ChatRoomExistedException: {}.", e.toString());
        return ResponseHandler.badRequest(ErrorCodeEnum.CHATROOM_EXISTED_ERROR);
    }
}
