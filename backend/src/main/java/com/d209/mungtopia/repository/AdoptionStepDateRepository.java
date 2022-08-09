package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.AdoptionStepDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdoptionStepDateRepository{
    EntityManager em;

    public void save(AdoptionStepDate adoptionStepDate) {
        em.persist(adoptionStepDate);
    }

    public AdoptionStepDate findOne(Long adoptionStepDateId){
        return em.find(AdoptionStepDate.class, adoptionStepDateId);
    }
}
