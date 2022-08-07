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

    public User findOne(Long userId) {
        return em.find(User.class, userId);
    }
}
