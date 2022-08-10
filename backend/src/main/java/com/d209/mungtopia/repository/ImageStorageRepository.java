package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.ImageStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImageStorageRepository {
    EntityManager em;

    public List<ImageStorage> findMainOne(Long boardId){
       return em.createQuery("select i from ImageStorage i where i.board.boardId = : boardId and i.orders = 1")
                .setParameter("boardId", boardId)
               .getResultList();
    }
}
