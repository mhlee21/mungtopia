package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Application;
import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.DogInfo;
import com.d209.mungtopia.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ManageApplicantRepository {

    @PersistenceContext
    private final EntityManager em;

    public List<User> findApplicationList(Long userId) {
        return em.createQuery("select u from User u where u.userSeq =: userId")
                .setParameter("userId", userId)
                .getResultList();
    }

    public Board findByBoardId(Long boardId){
        return em.find(Board.class, boardId);
    }

    public List<DogInfo> findDogInfoList() {
        return em.createQuery("select d from DogInfo d")
                .getResultList();
    }
}
