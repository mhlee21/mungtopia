package com.d209.mungtopia.dto.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameReq {
    private long userSeq;
    private boolean result;
    private int gameTag;
}
