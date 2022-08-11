package com.d209.mungtopia.dto.meeting.service;

import com.d209.mungtopia.dto.game.GameReq;
import com.d209.mungtopia.dto.game.MatchingGameReq;
import com.d209.mungtopia.entity.GameResult;

import java.util.List;

public interface GameService {
    List<GameReq> getGameResult(long userSeq);
    boolean postGameResult(GameReq gameReq);
    boolean postMatchingResult(MatchingGameReq gameReq);
}
