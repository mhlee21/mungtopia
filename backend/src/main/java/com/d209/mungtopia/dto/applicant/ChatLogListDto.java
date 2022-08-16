package com.d209.mungtopia.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatLogListDto {
    Long userSeq;
    String username;
    String nickname;
    String profile;
    List<ChatLogDto.Response> chatLogDtoList = new ArrayList<>();
}
