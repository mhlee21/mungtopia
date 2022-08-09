package com.d209.mungtopia.service;

import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.Comment;
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
    List<Comment> CommentAll();
    List<Comment> saveComment(Comment cmt);
    List<Comment> deleteComment(Comment cmt);


}
