package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.AdoptionStepDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AdoptionStepDateRepository {
    private final EntityManager em;

    public void delete(AdoptionStepDate stepDate){
        em.remove(stepDate);
    }

    public AdoptionStepDate find(Long id){
        return em.find(AdoptionStepDate.class, id);
    }
}
