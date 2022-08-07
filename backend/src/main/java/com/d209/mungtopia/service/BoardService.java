package com.d209.mungtopia.service;

import com.d209.mungtopia.entity.Board;

import java.util.List;

public interface BoardService {
    List<Board> findBoardAll(Long tagNo, int pageNo);

    Board findBoardDetail(Long boardId);

}
