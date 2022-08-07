package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Table(name = "dog_info", schema = "mungtopia", catalog = "")
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
    private Integer age;

    @Column(name = "weight")
    private String weight;

    @Column(name = "breed")
    private String breed;

    @Column(name = "vaccination")
    private Byte vaccination;

    @Column(name = "neutering")
    private Byte neutering;

    @Column(name = "adoption_status")
    private Byte adoptionStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
    @JsonIgnore
    private Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board boardByBoardId) {
        this.board = boardByBoardId;
    }
}
