package com.d209.mungtopia.service;

import com.d209.mungtopia.entity.Board;

import java.util.List;

public interface BoardService {
    List<Board> findBoardAll(Long tag_no);

    Board findBoardDetail(Long boardId);

}
