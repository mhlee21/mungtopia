package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.applicant.AppDto;
import com.d209.mungtopia.dto.board.*;
import com.d209.mungtopia.entity.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    List<BoardListDto.Response> findBoardAll(int tagNo, int pageNo, long userSeq);
    List<BoardListDto.Response> search(Long tagNo, int pageNo, long userSeq, String keyword);

    // 글 관련
    Board saveBoard(List<MultipartFile> multipartFiles, BoardDto boardDto) throws Exception;
    Board updateBoard(List<MultipartFile> multipartFiles, BoardDto boardDto, Board board) throws Exception;
    Boolean deleteBoard(Board board);
    Board findBoardDetail(Long boardId);

    Application saveApplication(Board board, AppDto appDto);
    Boolean likes(User user, Board board);
    Boolean unlikes(User user, Board board);
    Boolean star(User user, Board board);
    Boolean unstar(User user, Board board);
    List<CommentRes> CommentAll(Board board);
    List<CommentRes> saveComment(Board board, CommentDto commentDto);
    List<CommentRes> updateComment(Board board, Comment comment, CommentDto commentDto);
    List<CommentRes> deleteComment(Board board, Comment comment, CommentDto commentDto);
    List<Comment> saveReply(Board board, Comment comment, ReplyDto replyDto);
    List<Comment> updateReply(Board board, Reply reply, ReplyDto replyDto);
    List<Comment> deleteReply(Board board, Reply reply, ReplyDto replyDto);

    List<ImageStorage> saveImgFile(List<MultipartFile> multipartFiles,Board board) throws Exception;
    Resource getImgFile(String fileName) throws IOException;
}
