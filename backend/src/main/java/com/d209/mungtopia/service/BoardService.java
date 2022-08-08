package com.d209.mungtopia.service;

import com.d209.mungtopia.entity.Board;

import java.util.List;

public interface BoardService {
    List<Board> findBoardAll(Long tagNo, int pageNo);

    Board findBoardDetail(Long boardId);

    Boolean postBoardLike(Long boardId);
    Boolean deleteBoardLike(Long boardId);
    Boolean postBoardStar(Long boardId);
    Boolean deleteBoardStar(Long boardId);
}
