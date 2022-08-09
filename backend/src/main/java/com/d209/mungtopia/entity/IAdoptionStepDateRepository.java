package com.d209.mungtopia.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAdoptionStepDateRepository extends JpaRepository<AdoptionStepDate, Long> {
    @Override
    Optional<AdoptionStepDate> findById(Long Long);
}
