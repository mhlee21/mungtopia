package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfCommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard(Board board);
}
