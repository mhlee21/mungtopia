package com.d209.mungtopia.dto.manage;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class ChatLogDto {
    private Long userSeq;
    private String message;
    private LocalDateTime time;
}
