package com.d209.mungtopia.service;

import com.d209.mungtopia.entity.ChatLog;
import com.d209.mungtopia.entity.User;
import com.d209.mungtopia.repository.InfChatLogRepository;
import com.d209.mungtopia.repository.InfUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChattingService {
    private final InfUserRepository userRepository;
    private final InfChatLogRepository chatLogRepository;

    public ChatLog chattingHandler(ChatLog chatLog) {
        User user = userRepository.findById(chatLog.getUserSeq())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        chatLog.setUserSeq(user.getUserSeq());

        return chatLogRepository.save(chatLog);
    }
}
