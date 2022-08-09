package com.d209.mungtopia.controller;

import com.d209.mungtopia.common.ApiResponse;
import com.d209.mungtopia.dto.CommentDto;
import com.d209.mungtopia.dto.ReplyDto;
import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.Comment;
import com.d209.mungtopia.entity.Reply;
import com.d209.mungtopia.entity.User;
import com.d209.mungtopia.repository.*;
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
    private final InfCommentRepository commentRepository;
    private final InfReplyRepository replyRepository;
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

    @GetMapping("{board_id}/comments")
    @ApiOperation(value = "getCommentList - 댓글 목록 가져오기", notes = "댓글 목록 가져오기")
    public ApiResponse getCommentList (@PathVariable("board_id") Long boardId) {
        Board board = boardRepository.getReferenceById(boardId);
        return ApiResponse.success("data", boardService.CommentAll(board));
    }

    @PostMapping("{board_id}/comments")
    @ApiOperation(value = "saveComment - 댓글 달기", notes = "댓글 달기")
    public ApiResponse saveComment(
            @PathVariable("board_id") Long boardId,
            @RequestBody CommentDto commentDto
    ) {
        Board board = boardRepository.findById(boardId).get();
        // userSeq 와 userNickname 비교하여 유효성 검사 필요
        return ApiResponse.success("data", boardService.saveComment(board, commentDto));
    }

    @PutMapping("{board_id}/comments/{comment_id}")
    @ApiOperation(value = "updateComment - 댓글 수정", notes = "댓글 수정")
    public ApiResponse updateComment (
            @PathVariable("board_id") Long boardId,
            @PathVariable("comment_id") Long commentId,
            @RequestBody CommentDto commentDto
    ) {
        Board board = boardRepository.findById(boardId).get();
        Comment comment = commentRepository.findById(commentId).get();
        // 기존 userSeq 와 userNickname 비교하여 유효성 검사 필요
        return ApiResponse.success("data", boardService.updateComment(board, comment, commentDto));
    }

    @DeleteMapping("{board_id}/comments/{comment_id}")
    @ApiOperation(value = "deleteComment - 댓글 삭제", notes = "댓글 삭제")
    public ApiResponse deleteComment (
            @PathVariable("board_id") Long boardId,
            @PathVariable("comment_id") Long commentId,
            @RequestBody CommentDto commentDto
    ) {
        Board board = boardRepository.findById(boardId).get();
        Comment comment = commentRepository.findById(commentId).get();
        // 기존 userSeq 와 userNickname 비교하여 유효성 검사 필요
        return ApiResponse.success("data", boardService.deleteComment(board, comment, commentDto));
    }

    @PostMapping("{board_id}/comments/{comment_id}")
    @ApiOperation(value = "saveReply - 대댓글 달기", notes = "대댓글 달기")
    public ApiResponse saveReply(
            @PathVariable("board_id") Long boardId,
            @PathVariable("comment_id") Long commentId,
            @RequestBody ReplyDto replyDto
    ) {
        Board board = boardRepository.findById(boardId).get();
        Comment comment = commentRepository.findById(commentId).get();
        // userSeq 와 userNickname 비교하여 유효성 검사 필요
        return ApiResponse.success("data", boardService.saveReply(board, comment, replyDto));
    }

    @PutMapping("{board_id}/comments/{comment_id}/reply/{reply_id}")
    @ApiOperation(value = "updateReply - 대댓글 수정", notes = "대댓글 수정")
    public ApiResponse updateReply (
            @PathVariable("board_id") Long boardId,
            @PathVariable("comment_id") Long commentId,
            @PathVariable("reply_id") Long replyId,
            @RequestBody ReplyDto replyDto
    ) {
        Board board = boardRepository.findById(boardId).get();
//        Comment comment = commentRepository.findById(commentId).get();
        Reply reply = replyRepository.findById(replyId).get();
        // 기존 userSeq 와 userNickname 비교하여 유효성 검사 필요
        return ApiResponse.success("data", boardService.updateReply(board, reply, replyDto));
    }

    @DeleteMapping("{board_id}/comments/{comment_id}reply/{reply_id}")
    @ApiOperation(value = "deleteReply - 대댓글 삭제", notes = "대댓글 삭제")
    public ApiResponse deleteReply (
            @PathVariable("board_id") Long boardId,
            @PathVariable("comment_id") Long commentId,
            @PathVariable("reply_id") Long replyId,
            @RequestBody ReplyDto replyDto
    ) {
        Board board = boardRepository.findById(boardId).get();
//        Comment comment = commentRepository.findById(commentId).get();
        Reply reply = replyRepository.findById(replyId).get();
        // 기존 userSeq 와 userNickname 비교하여 유효성 검사 필요
        return ApiResponse.success("data", boardService.deleteReply(board, reply, replyDto));
    }

}
