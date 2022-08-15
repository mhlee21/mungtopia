package com.d209.mungtopia.controller;

import com.d209.mungtopia.common.ApiResponse;
import com.d209.mungtopia.dto.board.Keyword;
import com.d209.mungtopia.dto.applicant.AppDto;
import com.d209.mungtopia.dto.board.BoardDto;
import com.d209.mungtopia.dto.board.CommentDto;
import com.d209.mungtopia.dto.board.ReplyDto;
import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.Comment;
import com.d209.mungtopia.entity.Reply;
import com.d209.mungtopia.entity.User;
import com.d209.mungtopia.repository.*;
import com.d209.mungtopia.repository.user.UserRepository;
import com.d209.mungtopia.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/board")
@Api(value = "게시판 API", tags = "Board Controller - 게시판 API")
@RequiredArgsConstructor
public class BoardController {

    private final InfBoardRepository boardRepository;
    private final InfCommentRepository commentRepository;
    private final InfReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final BoardService boardService;

    @GetMapping("/{tag_no}")
    @ApiOperation(value = "mainInfo - 전체 글 불러오기", notes = "태그 별 게시글 리스트를 제공")
    public ApiResponse getBoardList(@PathVariable("tag_no") int tagNo,
                                    @RequestParam int pageNo, @RequestParam long userSeq) {
        return ApiResponse.success("data", boardService.findBoardAll(tagNo, pageNo, userSeq));
    }

    @GetMapping("/search/{tag_no}")
    @ApiOperation(value = "search - 검색", notes = "검색")
    public ApiResponse search(@PathVariable("tag_no") Long tagNo,
                              @RequestParam int pageNo,
                              @RequestParam long userSeq,
                              @RequestBody Keyword keyword) {
        return ApiResponse.success("data", boardService.search(tagNo, pageNo, userSeq, keyword.getKeyword()));
    }

    @PostMapping("/{tag_no}")
    @ApiOperation(value = "saveBoard - 글 쓰기", notes = "글 쓰기")
    public ApiResponse saveBoard(@PathVariable("tag_no") Long tagNo,
                                     @RequestBody BoardDto boardDto
                                ) {
        return ApiResponse.success("data", boardService.saveBoard(tagNo, boardDto));
    }

    @PutMapping("detail/{board_id}/{user_id}")
    @ApiOperation(value = "updateBoard - 글 수정 (boardTag 변경 불가능)", notes = "글 수정 (boardTag 변경 불가능)")
    public ApiResponse updateBoard(@PathVariable("board_id") Long boardId,
                                   @PathVariable("user_id") Long userId,
                                   @RequestBody BoardDto boardDto) {
        Board board = boardRepository.findById(boardId).get();
        User user = userRepository.findById(userId).get();
        if (board.getUser() == user) {
            return ApiResponse.success("data", boardService.updateBoard(board, boardDto));
        } else {
            return ApiResponse.fail();
        }
    }
    @DeleteMapping("detail/{board_id}/{user_id}")
    @ApiOperation(value = "deleteBoard - 글 삭제", notes = "글 삭제")
    public ApiResponse deleteBoard(@PathVariable("board_id") Long boardId,
                                   @PathVariable("user_id") Long userId) {
        Board board = boardRepository.findById(boardId).get();
        User user = userRepository.findById(userId).get();
        if (board.getUser() == user) {
            return ApiResponse.success("data", boardService.deleteBoard(board));
        } else {
            return ApiResponse.fail();
        }
    }

    @GetMapping("detail/{board_id}")
    @ApiOperation(value = "boardDetailInfo - 상세 글 불러오기", notes = "상세 게시글 정보 가져오기")
    public ApiResponse boardDetailInfo(@PathVariable("board_id") Long boardId) {
        return ApiResponse.success("data", boardService.findBoardDetail(boardId));
    }

    @PostMapping("detail/{board_id}/applicant")
    @ApiOperation(value = "saveApplication - 입양 신청서 작성하기", notes = "입양 신청서 작성하기")
    public ApiResponse saveApplication(@PathVariable("board_id") Long boardId,
                                       @RequestBody AppDto appDto) {
        Board board = boardRepository.findById(boardId).get();
        return ApiResponse.success("data", boardService.saveApplication(board, appDto));
    }

    @PostMapping("like/{board_id}")
    @ApiOperation(value = "likes - 좋아요 하기", notes = "좋아요 하기")
    public ApiResponse likes(
            @PathVariable("board_id") Long boardId,
            @RequestBody Long userId
        ) {
        User user = userRepository.getReferenceById(userId);
        Optional<Board> board = boardRepository.findById(boardId);

        return ApiResponse.success("data", boardService.likes(user, board.get()));
    }

    @DeleteMapping("like/{board_id}")
    @ApiOperation(value = "unlikes - 좋아요 삭제", notes = "좋아요 삭제")
    public ApiResponse unlikes(
            @PathVariable("board_id") Long boardId,
            @RequestBody Long userId
    ) {
        User user = userRepository.getReferenceById(userId);
        Optional<Board> board = boardRepository.findById(boardId);
        return ApiResponse.success("data", boardService.unlikes(user, board.get()));
    }

    @PostMapping("star/{board_id}")
    @ApiOperation(value = "star - 별표 하기", notes = "별표 하기")
    public ApiResponse star(
            @PathVariable("board_id") Long boardId,
            @RequestBody Long userId
    ) {
        User user = userRepository.getReferenceById(userId);
        Optional<Board> board = boardRepository.findById(boardId);
        return ApiResponse.success("data", boardService.star(user, board.get()));
    }

    @DeleteMapping("star/{board_id}")
    @ApiOperation(value = "unstar - 별표 삭제", notes = "별표 삭제")
    public ApiResponse unstar(
            @PathVariable("board_id") Long boardId,
            @RequestBody Long userId
    ) {
        User user = userRepository.getReferenceById(userId);
        Optional<Board> board = boardRepository.findById(boardId);
        return ApiResponse.success("data", boardService.unstar(user, board.get()));
    }

    @GetMapping("{board_id}/comments")
    @ApiOperation(value = "getCommentList - 댓글 목록 가져오기", notes = "댓글 목록 가져오기")
    public ApiResponse getCommentList (@PathVariable("board_id") Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        return ApiResponse.success("data", boardService.CommentAll(board.get()));
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

    @DeleteMapping("{board_id}/comments/{comment_id}/reply/{reply_id}")
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

    @PostMapping("/img/{boardId}")
    public ApiResponse saveImgFile(@RequestParam("files") List<MultipartFile> multipartFiles, @PathVariable long boardId) throws Exception {
        return ApiResponse.success("data", boardService.saveImgFile(multipartFiles, boardId));
    }
}
