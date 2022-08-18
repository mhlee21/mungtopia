package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Application;
import com.d209.mungtopia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findApplicationByUser(User user);
}
