package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class BoardTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_tag_id")
    private Integer boardTagId;
    private String name;
}
