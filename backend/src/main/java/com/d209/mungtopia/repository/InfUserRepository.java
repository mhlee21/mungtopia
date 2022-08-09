package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfUserRepository extends JpaRepository<User, Long> {
}
