package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.AdoptionProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AdoptionProcessRepository {
    private final EntityManager em;

    public AdoptionProcess find(Long adoptionProcessId){
       return em.find(AdoptionProcess.class, adoptionProcessId);
    }
}
