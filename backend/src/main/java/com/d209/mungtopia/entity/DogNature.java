package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Table(name = "dog_nature", schema = "mungtopia", catalog = "")
public class DogNature {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dog_nature_id")
    private long dogNatureId;
    @Basic
    @Column(name = "feature")
    private String feature;

    @ManyToOne
    @JoinColumn(name = "dog_info_id", referencedColumnName = "dog_info_id", nullable = false)
    private DogInfo dogInfoByDogInfoId;


    public DogInfo getDogInfoByDogInfoId() {
        return dogInfoByDogInfoId;
    }

    public void setDogInfoByDogInfoId(DogInfo dogInfoByDogInfoId) {
        this.dogInfoByDogInfoId = dogInfoByDogInfoId;
    }
}
