package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfBoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b where b.boardTag = :boardTag and b.contents like CONCAT('%',:keyword,'%') order by b.createtime desc")
    List<Board> findAllByBoardTag (@Param("boardTag") String boardTag, @Param("boardTag") String keyword);

    @Query("select b from Board b where b.contents like CONCAT('%',:keyword,'%') order by b.createtime desc")
    List<Board> findAll( @Param("keyword") String keyword);

    @Query("select b from Board b order by b.createtime desc")
    Page<Board> findAll(Pageable pageable);

    Page<Board> findByBoardTagOrderByCreatetimeDesc(String boardTag, Pageable pageable);

//    @Query("select b from Board b where b.boardId not in (:boardId) and b.boardTag like :tageName order by b.createtime desc")
    Page<Board> findByBoardIdNotInAndBoardTagOrderByCreatetimeDesc(List<Long> boardIds, String tagName, Pageable pageable);


}
