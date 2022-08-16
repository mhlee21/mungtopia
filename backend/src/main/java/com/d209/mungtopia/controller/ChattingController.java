package com.d209.mungtopia.controller;

import com.d209.mungtopia.common.ApiResponse;
import com.d209.mungtopia.dto.applicant.ChatLogDto;
import com.d209.mungtopia.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChattingController {
    private final ChattingService chattingService;

    @MessageMapping("/receive")
    @SendTo("/send")
    public ChatLogDto.Response chattingHandler(ChatLogDto.Request chatLogDto){
        return chattingService.chattingHandler(chatLogDto);
    }

    @GetMapping("/log")
    public ApiResponse chatLog(@RequestParam("page") int page,
                               @RequestParam("chatRoomId") Long chatRoomId,
                               @RequestParam("userSeq") Long userSeq) {
        return ApiResponse.success("data", chattingService.chatLog(page, chatRoomId, userSeq));
    }

}
