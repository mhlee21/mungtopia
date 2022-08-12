package com.d209.mungtopia.controller;

import com.d209.mungtopia.common.ApiResponse;
import com.d209.mungtopia.dto.applicant.ChatLogDto;
import com.d209.mungtopia.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChattingController {
    private final ChattingService chattingService;

    @MessageMapping("/receive")
    @SendTo("/send")
    public ChatLogDto chattingHandler(ChatLogDto chatLogDto){
        return chattingService.chattingHandler(chatLogDto);
    }

    @GetMapping("/log/{page_no}/{chat_room_id}")
    public ApiResponse chatLog(@PathVariable("page_no") int pageNo,
                               @PathVariable("chat_room_id") Long chatRoomId,
                               Pageable pageable) {
        return ApiResponse.success("data", chattingService.chatLog(pageNo, chatRoomId, pageable));
    }

}
