package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.applicant.ChatLogDto;
import com.d209.mungtopia.dto.applicant.ChatLogListDto;
import com.d209.mungtopia.entity.ChatLog;
import com.d209.mungtopia.entity.ChatRoom;
import com.d209.mungtopia.entity.User;
import com.d209.mungtopia.repository.InfChatLogRepository;
import com.d209.mungtopia.repository.InfChatRoomRepository;
import com.d209.mungtopia.repository.InfUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingService {
    private final InfUserRepository userRepository;
    private final InfChatLogRepository chatLogRepository;
    private final InfChatRoomRepository chatRoomRepository;

    public Timestamp getNow() {
        return new Timestamp(System.currentTimeMillis());
    }

    public ChatLogDto.Response chattingHandler(ChatLogDto.Request chatLogDto) {
        User user = userRepository.findById(chatLogDto.getUserSeq())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ChatRoom chatRoom = chatRoomRepository.findById(chatLogDto.getChatRoomId()).get();
        ChatLog chatLog = ChatLog.builder()
                        .userSeq(chatLogDto.getUserSeq())
                        .content(chatLogDto.getContent())
                        .createtime(getNow())
                        .chatRoom(chatRoom)
                        .build();
        chatLogRepository.save(chatLog);

        return new ChatLogDto.Response(
                chatLogDto.getUserSeq(),
                chatLogDto.getContent(),
                chatLogDto.getCreatetime()
                        .toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                chatLogDto.getChatRoomId()
        );
    }

    public ChatLogListDto chatLog(int page, Long chatRoomId, Long userSeq) {
        PageRequest pageRequest = PageRequest.of(page, 20);
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).get();
        User you = new User();
        if (chatRoom.getApplicantId().equals(userSeq)) {
            you = userRepository.findById(chatRoom.getProtectorId()).get();
        } else {
            you = userRepository.findById(chatRoom.getApplicantId()).get();
        }

        Page<ChatLog> chatLogs = chatLogRepository.findByChatRoomOrderByCreatetime(chatRoom, pageRequest);
        List<ChatLogDto.Response> chatLogRes = new ArrayList<>();
        List<ChatLog> logsContent = new ArrayList<>(chatLogs.getContent());

        // 리스트 뒤집기 (최신메세지를 제일 마지막으로 보내기 위함)
        List<ChatLog> reverseList = new ArrayList<>();
        for(int i = logsContent.size() - 1; i>=0; i--){
            reverseList.add(logsContent.get(i));
        }

        for(ChatLog chatLog: reverseList) {
            ChatLogDto.Response clr = new ChatLogDto.Response(
                    chatLog.getUserSeq(),
                    chatLog.getContent(),
                    chatLog.getCreatetime()
                            .toLocalDateTime()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    chatLog.getChatRoom().getChatRoomId()
            );
            chatLogRes.add(clr);
        }
        ChatLogListDto chatLogListDto = new ChatLogListDto(
                you.getUserSeq(),
                you.getUsername(),
                you.getNickname(),
                you.getProfileImageUrl(),
                chatLogRes
        );
        return chatLogListDto;
    }
}
