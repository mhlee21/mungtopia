package com.d209.mungtopia.repository;

import com.d209.mungtopia.dto.protector.protectorBoardList;
import com.d209.mungtopia.entity.Application;
import com.d209.mungtopia.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
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
    public List<protectorBoardList> findBoardList(Long userId){
        List<Board> boardList = em.createQuery("select b from Board b where b.userSeq = :userId and b.boardTag =: boardName")
                .setParameter("userId", userId)
                .setParameter("boardName", "입양")
                .getResultList();

        List<protectorBoardList> protectorBoardLists = new ArrayList<>();

        for (int i = 0; i < boardList.size(); i++) {
            protectorBoardList protectorBoard = new protectorBoardList();

            Board board = boardList.get(i);

            protectorBoard.setBoardId(board.getBoardId());
            protectorBoard.setAdoptionStatus(board.getDogInfo().getAdoptionStatus());
            protectorBoard.setDogName(board.getDogInfo().getName());

            for (int j = 0; j < board.getImageStorageList().size(); j++) {
                Integer orders = board.getImageStorageList().get(j).getOrders();
                if (orders.equals(1)){
                    protectorBoard.setDogImgUrl(board.getImageStorageList().get(j).getFilename());
                }
            }

            protectorBoardLists.add(protectorBoard);
        }

        return protectorBoardLists;
}

    public List<Application> findApplicantList(Long boardId) {
        List<Application> applicationList = em.createQuery("select a from Application a where a.boardId := boardId", Application.class)
                .setParameter("boardId", boardId)
                .getResultList();
        return null;
    }

    public void findApplicantDetailList(Long applicationProcessId){

    }

}
