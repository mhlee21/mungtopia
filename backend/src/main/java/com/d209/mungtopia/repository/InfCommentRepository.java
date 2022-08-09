package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfCommentRepository extends JpaRepository<Comment, Long> {
}
