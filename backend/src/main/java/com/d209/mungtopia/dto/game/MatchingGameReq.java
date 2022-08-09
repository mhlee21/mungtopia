package com.d209.mungtopia.dto.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MatchingGameReq {
    private long userSeq;
    private List<Integer> result = new ArrayList<>();
}
