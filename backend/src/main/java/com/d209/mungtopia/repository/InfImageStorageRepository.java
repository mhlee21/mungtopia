package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.ImageStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfImageStorageRepository extends JpaRepository<ImageStorage, Long> {
    ImageStorage findByBoardAndOrders(Board board, int order);
}