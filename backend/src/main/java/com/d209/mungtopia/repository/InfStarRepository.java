package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfStarRepository extends JpaRepository<Star, Long> {
}
