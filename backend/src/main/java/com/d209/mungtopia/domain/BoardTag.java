package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class BoardTag {
    @Id
    @GeneratedValue
    private Integer boardTagId;
    private String name;
}
