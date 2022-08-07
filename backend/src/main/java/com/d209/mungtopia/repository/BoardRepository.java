package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findBoardAll() {
        return em.createQuery("select b from Board b")
                .getResultList();
    }

    public Board findByBoardId(Long boardId){
        return em.find(Board.class, boardId);
    }

}
