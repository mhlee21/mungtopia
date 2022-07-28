package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class DogInfo {

    @Id @GeneratedValue
    private Long dogInfoId;

    private String name;
    private String areaSido;
    private String areaGugun;
    private char gender;
    private int age;
    private String weight;
    private String breed;
    private boolean vaccination;
    private boolean neutering;
    private boolean adoptionStatus;

}
