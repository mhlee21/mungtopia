package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "dog_nature", schema = "mungtopia")
public class DogNature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_nature_id")
    private long dogNatureId;

    @Column(name = "nature1")
    private String nature1;

    @Column(name = "nature2")
    private String nature2;

    @Column(name = "nature3")
    private String nature3;

    @Column(name = "nature4")
    private String nature4;

    @Column(name = "nature5")
    private String nature5;

    @Column(name = "nature6")
    private String nature6;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_info_id", referencedColumnName = "dog_info_id", nullable = false)
    @JsonIgnore
    private DogInfo dogInfo;
}
