package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.AdoptionProcess;
import com.d209.mungtopia.entity.AdoptionStepDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfAdoptionStepDateRepository extends JpaRepository<AdoptionStepDate, Long> {
        Optional<AdoptionStepDate> findByAdoptionProcessAndStep(AdoptionProcess adoptionProcess, int step);
        List<AdoptionStepDate> findByAdoptionProcessOrderByStep(AdoptionProcess adoptionProcess);
}
