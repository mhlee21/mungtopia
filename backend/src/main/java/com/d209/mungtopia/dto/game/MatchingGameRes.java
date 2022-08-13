package com.d209.mungtopia.dto.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchingGameRes {
    private Long boardId;
    private String dogName;
    private String dogImg;
    private int percent;
}
