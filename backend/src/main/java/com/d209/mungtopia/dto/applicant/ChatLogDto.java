package com.d209.mungtopia.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatLogDto {
    private Long userSeq;
    private String content;
    private LocalDateTime createtime;
    private Boolean isRequest;
    private Long chatRoomId;
}
