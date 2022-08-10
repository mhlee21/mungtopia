package com.d209.mungtopia.controller;

import com.d209.mungtopia.common.ApiResponse;
import com.d209.mungtopia.dto.game.GameReq;
import com.d209.mungtopia.dto.game.MatchingGameReq;
import com.d209.mungtopia.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/game")
@Api(value = "게임", tags = "Game Controller - 게임 API")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping("/{user_seq}")
    @ApiOperation(value = "getGameResult - 게임 가져오기", notes = "지금까지 한 게임 결과 - true 인 것만 나옴")
    public ApiResponse getGameResult(@PathVariable("user_seq") long userSeq){
        return ApiResponse.success("data", gameService.getGameResult(userSeq));
    }

    @PostMapping("/")
    @ApiOperation(value = "postGameResult - 게임 저장", notes = "게임 결과 저장 - 항상 true")
    public ApiResponse postGameResult(@RequestBody GameReq gameReq){
        return ApiResponse.success("data", gameService.postGameResult(gameReq));
    }

    @PostMapping("/matching/")
    @ApiOperation(value = "postMatchingResult - 매칭 게임 결과 저장", notes = "매칭 게임 결과 저장")
    public ApiResponse postMatchingResult(@RequestBody MatchingGameReq gameReq){
        System.out.println("in");
        return ApiResponse.success("data", gameService.postMatchingResult(gameReq));
    }
}
