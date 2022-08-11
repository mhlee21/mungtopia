package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfAnswerRepository extends JpaRepository<Answer, Long> {
}
