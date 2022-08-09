package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ManageProtectorRepository {

    @PersistenceContext // 생략 가능
    private final EntityManager em;

    /**
     *
     * @param userId
     * @return List<protectorBoardList>
     */
    public List<Board> findBoardList(Long userId){
        return em.createQuery("select b from Board b where b.user.userSeq = :userId and b.boardTag =: boardName", Board.class)
                .setParameter("userId", userId)
                .setParameter("boardName", "입양")
                .getResultList();
    }

    public List<Application> findApplicantList(Long boardId) {
        return em.createQuery("select a from Application a where a.boardId =: boardId")
                .setParameter("boardId", boardId)
                .getResultList();
    }

    public Board findByBoardId(Long boardId){
        return em.find(Board.class, boardId);
    }

   public AdoptionProcess findByAdoptionProcessId(Long adoptionProcessId){
        return em.find(AdoptionProcess.class, adoptionProcessId);
   }

}
