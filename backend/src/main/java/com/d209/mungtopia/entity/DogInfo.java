package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class DogInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_info_id")
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
