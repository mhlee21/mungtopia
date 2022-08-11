package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
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
    private int nature1;

    @Column(name = "nature2")
    private int nature2;

    @Column(name = "nature3")
    private int nature3;

    @Column(name = "nature4")
    private int nature4;

    @Column(name = "nature5")
    private int nature5;

    @Column(name = "nature6")
    private int nature6;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_info_id", referencedColumnName = "dog_info_id", nullable = false)
    @JsonIgnore
    private DogInfo dogInfo;
}
