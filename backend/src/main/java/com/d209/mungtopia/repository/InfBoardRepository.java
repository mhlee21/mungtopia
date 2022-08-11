package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfBoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b where b.boardTag = :boardTag order by b.createtime desc")
    List<Board> findAllByBoardTag (@Param("boardTag") String boardTag);
}
