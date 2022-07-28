package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class GameTag {
    @Id
    @GeneratedValue
    private Integer game_tag_id;
    private String name;
}
