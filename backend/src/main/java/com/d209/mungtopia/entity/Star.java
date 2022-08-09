package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
public class Star {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "star_id")
    private long starId;

    @Column(name = "createtime")
    private Timestamp createtime;

    @ManyToOne
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq", nullable = false)
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
    private Board board;
}
