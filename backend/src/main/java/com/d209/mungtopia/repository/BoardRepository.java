package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.ImageStorage;
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

    public Board findOne(Long boardId){
        return em.find(Board.class, boardId);
    }


    public List<ImageStorage> findImageStorage(Long boardId){
        return em.createQuery("select b.imageStorageList from Board b where b.boardId =: boardId")
                .setParameter("boardId", boardId)
                .getResultList();
    }

    public String findMainImg(Long boardId){
        List<ImageStorage> imageStorage = findImageStorage(boardId);
//        imageStorage.stream().filter
        for (ImageStorage img: imageStorage) {
            if (img.getOrders() == 1)
                return img.getOriginFileName();
        }
        return null;
    }
}
