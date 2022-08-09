package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.Star;
import com.d209.mungtopia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfStarRepository extends JpaRepository<Star, Long> {
    Optional<Star> findStarByUserAndBoard(User user, Board board);
}
