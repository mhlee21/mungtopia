package com.d209.mungtopia.dto.manage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChatDto {
    private Long userSeq;
    private Long userNickname;
    private String profile;
    private List<ChatLogDto> chatLogDtoList = new ArrayList<>();
}
