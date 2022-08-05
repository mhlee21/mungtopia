package com.d209.mungtopia.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
@Repository
@RequiredArgsConstructor
public class LikeRepository {
    EntityManager em;
}
