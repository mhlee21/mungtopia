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
    public List<Board> findBoardAll(Long tagNo, int pageNo) {
        /*
         * 전체 : 0
         * 입양 : 1 (추천하는 강아지 글 3개 먼저 보여주고 나머지는 최신순으로 보여줌
         * 후기 : 2
         * 자유 : 3
         */
//        System.out.printf("%d %d\n", tagNo, pageNo);
        List<Board> boardList = boardRepository.findBoardAll();
        List<DogInfo> dogInfoList = dogInfoRepository.findDogInfoAll();


        return null;
    }

    @Override
    public Board findBoardDetail(Long boardId) {
        return boardRepository.findOne(boardId);
    }
}
