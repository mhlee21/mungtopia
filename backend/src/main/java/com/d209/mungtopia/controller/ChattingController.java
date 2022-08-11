package com.d209.mungtopia.controller;

import com.d209.mungtopia.dto.ChatLogDto;
import com.d209.mungtopia.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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

}
