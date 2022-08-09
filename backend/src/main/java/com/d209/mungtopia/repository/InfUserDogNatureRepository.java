package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.User;
import com.d209.mungtopia.entity.UserDogNature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfUserDogNatureRepository extends JpaRepository<UserDogNature, Long> {
    Optional<UserDogNature> findByUser(User user);
}
