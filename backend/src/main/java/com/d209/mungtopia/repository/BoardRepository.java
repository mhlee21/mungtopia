package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public Board findByBoardId(Long boardId){
        return em.find(Board.class, boardId);
    }

}
