package com.d209.mungtopia.service;

import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.DogInfo;
import com.d209.mungtopia.repository.BoardRepository;
import com.d209.mungtopia.repository.DogInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final DogInfoRepository dogInfoRepository;

    @Override
    public List<Board> findBoardAll(Long tag_no) {
        List<Board> boardList = boardRepository.findBoardAll();
        List<DogInfo> dogInfoList = dogInfoRepository.findDogInfoAll();


        return null;
    }

    @Override
    public Board findBoardDetail(Long boardId) {
        return null;
    }
}
