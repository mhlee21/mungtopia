package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class GameTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_tag_id")
    private Integer gameTagId;
    private String name;
}
