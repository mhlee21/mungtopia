package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.ChatLogDto;
import com.d209.mungtopia.entity.ChatLog;
import com.d209.mungtopia.entity.ChatRoom;
import com.d209.mungtopia.entity.User;
import com.d209.mungtopia.repository.InfChatLogRepository;
import com.d209.mungtopia.repository.InfChatRoomRepository;
import com.d209.mungtopia.repository.InfUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChattingService {
    private final InfUserRepository userRepository;
    private final InfChatLogRepository chatLogRepository;
    private final InfChatRoomRepository chatRoomRepository;

    public ChatLogDto chattingHandler(ChatLogDto chatLogDto) {
        User user = userRepository.findById(chatLogDto.getUserSeq())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        System.out.println(chatLogDto.getChatRoomId());
        System.out.println(chatLogDto.getContent());
        System.out.println(chatLogDto.getCreatetime());
        System.out.println(chatLogDto.getIsRequest());
        System.out.println(chatLogDto.getUserSeq());

        ChatRoom chatRoom = chatRoomRepository.findById(chatLogDto.getChatRoomId()).get();
        ChatLog chatLog = ChatLog.builder()
                        .userSeq(chatLogDto.getUserSeq())
                        .content(chatLogDto.getContent())
                        .createtime(chatLogDto.getCreatetime())
                        .isRequest(chatLogDto.getIsRequest())
                        .chatRoom(chatRoom)
                        .build();
        chatLogRepository.save(chatLog);
        return chatLogDto;
    }
}
