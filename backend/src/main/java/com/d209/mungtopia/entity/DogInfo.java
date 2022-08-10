package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "dog_info", schema = "mungtopia")
public class DogInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dog_info_id")
    private Long dogInfoId;

    @Column(name = "name")
    private String name;

    @Column(name = "area_sido")
    private String areaSido;

    @Column(name = "area_gugun")
    private String areaGugun;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;

    @Column(name = "weight")
    private String weight;

    @Column(name = "breed")
    private String breed;

    @Column(name = "vaccination")
    private Boolean vaccination;

    @Column(name = "neutering")
    private Boolean neutering;

    @Column(name = "adoption_status")
    private Boolean adoptionStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
    @JsonIgnore
    private Board board;

    @OneToOne(mappedBy = "dogInfo", fetch = FetchType.LAZY)
    private DogNature dogNature;
}
