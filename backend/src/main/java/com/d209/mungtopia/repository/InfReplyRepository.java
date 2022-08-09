package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfReplyRepository extends JpaRepository<Reply, Long> {
}
