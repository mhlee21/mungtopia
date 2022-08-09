package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.CommentDto;
import com.d209.mungtopia.dto.ReplyDto;
import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.Comment;
import com.d209.mungtopia.entity.Reply;
import com.d209.mungtopia.entity.User;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    List<Board> findBoardAll(Long tagNo, int pageNo);

    Optional<Board> findBoardDetail(Long boardId);

    Boolean likes(User user, Board board);
    Boolean unlikes(User user, Board board);
    Boolean star(User user, Board board);
    Boolean unstar(User user, Board board);
    List<Comment> CommentAll(Board board);
    List<Comment> saveComment(Board board, CommentDto commentDto);
    List<Comment> updateComment(Board board, Comment comment, CommentDto commentDto);
    List<Comment> deleteComment(Board board, Comment comment, CommentDto commentDto);
    List<Comment> saveReply(Board board, Comment comment, ReplyDto replyDto);
    List<Comment> updateReply(Board board, Reply reply, ReplyDto replyDto);
    List<Comment> deleteReply(Board board, Reply reply, ReplyDto replyDto);
}
