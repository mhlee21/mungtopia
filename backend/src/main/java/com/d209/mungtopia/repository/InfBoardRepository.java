package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfBoardRepository extends JpaRepository<Board, Long> {
}
