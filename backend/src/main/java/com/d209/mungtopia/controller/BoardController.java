package com.d209.mungtopia.controller;

import com.d209.mungtopia.common.ApiResponse;
import com.d209.mungtopia.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/board")
@Api(value = "게시판 API", tags = "Board Controller - 게시판 API")
@RequiredArgsConstructor
public class BoardController {

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
    @ApiOperation(value = "postBoardLike - 좋아요 하기", notes = "좋아요 하기")
    public ApiResponse postBoardLike(@PathVariable("board_id") Long boardId) {
        return ApiResponse.success("data", boardService.postBoardLike(boardId));
    }

    @DeleteMapping("like/{board_id}")
    @ApiOperation(value = "deleteBoardLike - 좋아요 삭제", notes = "좋아요 삭제")
    public ApiResponse deleteBoardLike(@PathVariable("board_id") Long boardId) {
        return ApiResponse.success("data", boardService.deleteBoardLike(boardId));
    }

    @PostMapping("star/{board_id}")
    @ApiOperation(value = "postBoardStar - 별표 하기", notes = "별표 하기")
    public ApiResponse postBoardStar(@PathVariable("board_id") Long boardId) {
        return ApiResponse.success("data", boardService.postBoardStar(boardId));
    }

    @DeleteMapping("star/{board_id}")
    @ApiOperation(value = "deleteBoardStar - 별표 삭제", notes = "별표 삭제")
    public ApiResponse deleteBoardStar(@PathVariable("board_id") Long boardId) {
        return ApiResponse.success("data", boardService.deleteBoardStar(boardId));
    }
}
