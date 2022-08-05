package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CUserRepository {
    private final EntityManager em;

    public List<User> findUserList(Long userId) {
        return em.createQuery("select u from User u where u.userSeq =: userId")
                .setParameter("userId", userId)
                .getResultList();
    }
}
