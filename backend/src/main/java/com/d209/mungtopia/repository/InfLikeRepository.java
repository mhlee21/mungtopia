package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.Likes;
import com.d209.mungtopia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfLikeRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findLikesByUserAndBoard(User user, Board board);
    Long countByBoard(Board board);
}
