package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.DogNature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfDogNatureRepository extends JpaRepository<DogNature, Long> {
}
