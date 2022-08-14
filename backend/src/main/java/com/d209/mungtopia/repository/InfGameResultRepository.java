package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.GameResult;
import com.d209.mungtopia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfGameResultRepository extends JpaRepository<GameResult, Long> {
    Optional<GameResult> findByUserAndGameTag(User user, int gameTag);
    List<GameResult> findByUserOrderByGameTagAsc(User user);
}
