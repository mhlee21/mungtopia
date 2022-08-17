package com.d209.mungtopia.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class ChatLogDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long userSeq;
        private String content;
        private Timestamp createtime;
        private Long chatRoomId;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long userSeq;
        private String content;
        private String createtime;
        private Long chatRoomId;
    }
}
