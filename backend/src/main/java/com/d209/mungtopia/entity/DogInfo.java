package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Table(name = "dog_info", schema = "mungtopia", catalog = "")
public class DogInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dog_info_id")
    private long dogInfoId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "area_sido")
    private String areaSido;
    @Basic
    @Column(name = "area_gugun")
    private String areaGugun;
    @Basic
    @Column(name = "gender")
    private String gender;
    @Basic
    @Column(name = "age")
    private Integer age;
    @Basic
    @Column(name = "weight")
    private String weight;
    @Basic
    @Column(name = "breed")
    private String breed;
    @Basic
    @Column(name = "vaccination")
    private Byte vaccination;
    @Basic
    @Column(name = "neutering")
    private Byte neutering;
    @Basic
    @Column(name = "adoption_status")
    private Byte adoptionStatus;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
    private Board boardByBoardId;

    public Board getBoardByBoardId() {
        return boardByBoardId;
    }

    public void setBoardByBoardId(Board boardByBoardId) {
        this.boardByBoardId = boardByBoardId;
    }
}
