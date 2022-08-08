package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Application;
import com.d209.mungtopia.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplicationRepository {
    private final EntityManager em;

    public Application findOne(Long appId){
        return em.find(Application.class, appId);
    }

    public List<Application> findApplicationByUserId(Long userId) {
        User user = em.find(User.class, userId);
        return em.createQuery("select a from Application a where a.user =: user")
                .setParameter("user", user)
                .getResultList();
    }
}
