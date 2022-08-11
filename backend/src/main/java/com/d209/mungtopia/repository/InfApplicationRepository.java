package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfApplicationRepository extends JpaRepository<Application, Long> {

}
