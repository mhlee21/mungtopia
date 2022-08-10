package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.DogInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DogInfoRepository {
    private final EntityManager em;

    public List<DogInfo> findDogInfoAll() {
        return em.createQuery("select d from DogInfo d")
                .getResultList();
    }
}