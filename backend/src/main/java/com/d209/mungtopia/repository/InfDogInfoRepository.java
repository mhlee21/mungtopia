package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.DogInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfDogInfoRepository extends JpaRepository<DogInfo, Long> {
}
