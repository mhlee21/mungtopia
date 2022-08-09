package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.AdoptionProcess;
import com.d209.mungtopia.entity.AdoptionStepDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdoptionProcessRepository {
    private final EntityManager em;

    public AdoptionProcess find(Long adoptionProcessId){
       return em.find(AdoptionProcess.class, adoptionProcessId);
    }

    public List<AdoptionStepDate> findAdoptionStepDateList(Long adoptionProcessId) {
        List<AdoptionStepDate> adoptionStepDateList = em.find(AdoptionProcess.class, adoptionProcessId).getAdoptionStepDateList();
        adoptionStepDateList.sort(new Comparator<AdoptionStepDate>() {
            @Override
            public int compare(AdoptionStepDate o1, AdoptionStepDate o2) {
                return Integer.compare(o1.getStep(), o1.getStep());
            }
        });
        return adoptionStepDateList;
    }
}
