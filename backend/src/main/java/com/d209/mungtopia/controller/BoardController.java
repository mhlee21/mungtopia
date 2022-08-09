package com.d209.mungtopia.controller;

import com.d209.mungtopia.common.ApiResponse;
import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.User;
import com.d209.mungtopia.repository.CUserRepository;
import com.d209.mungtopia.repository.InfBoardRepository;
import com.d209.mungtopia.repository.InfUserRepository;
import com.d209.mungtopia.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/board")
@Api(value = "게시판 API", tags = "Board Controller - 게시판 API")
@RequiredArgsConstructor
public class BoardController {

    private final InfBoardRepository boardRepository;
    private final InfUserRepository userRepository;
    private final BoardService boardService;

    @GetMapping("{tag_no}")
    @ApiOperation(value = "mainInfo - 전체 글 불러오기", notes = "태그 별 게시글 리스트를 제공")
    public ApiResponse getBoardList(@PathVariable("tag_no") Long tagNo,
                                    @RequestParam int pageNo) {
        return ApiResponse.success("data", boardService.findBoardAll(tagNo, pageNo));
    }

    @GetMapping("detail/{board_id}")
    @ApiOperation(value = "boardDetailInfo - 상세 글 불러오기", notes = "상세 게시글 정보 가져오기")
    public ApiResponse boardDetailInfo(@PathVariable("board_id") Long boardId) {
        return ApiResponse.success("data", boardService.findBoardDetail(boardId));
    }

    @PostMapping("like/{board_id}")
    @ApiOperation(value = "likes - 좋아요 하기", notes = "좋아요 하기")
    public ApiResponse likes(
            @PathVariable("board_id") Long boardId,
            @RequestBody Long userId
        ) {
        User user = userRepository.getReferenceById(userId);
        Board board = boardRepository.getReferenceById(boardId);
        return ApiResponse.success("data", boardService.likes(user, board));
    }

    @DeleteMapping("like/{board_id}")
    @ApiOperation(value = "unlikes - 좋아요 삭제", notes = "좋아요 삭제")
    public ApiResponse unlikes(
            @PathVariable("board_id") Long boardId,
            @RequestBody Long userId
    ) {
        User user = userRepository.getReferenceById(userId);
        Board board = boardRepository.getReferenceById(boardId);
        return ApiResponse.success("data", boardService.unlikes(user, board));
    }

    @PostMapping("star/{board_id}")
    @ApiOperation(value = "star - 별표 하기", notes = "별표 하기")
    public ApiResponse star(
            @PathVariable("board_id") Long boardId,
            @RequestBody Long userId
    ) {
        User user = userRepository.getReferenceById(userId);
        Board board = boardRepository.getReferenceById(boardId);
        return ApiResponse.success("data", boardService.star(user, board));
    }

    @DeleteMapping("star/{board_id}")
    @ApiOperation(value = "unstar - 별표 삭제", notes = "별표 삭제")
    public ApiResponse unstar(
            @PathVariable("board_id") Long boardId,
            @RequestBody Long userId
    ) {
        User user = userRepository.getReferenceById(userId);
        Board board = boardRepository.getReferenceById(boardId);
        return ApiResponse.success("data", boardService.unstar(user, board));
    }
}
