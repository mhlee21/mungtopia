package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class Board {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "board_id")
    private long boardId;
    @Basic
    @Column(name = "board_tag")
    private String boardTag;
    @Basic
    @Column(name = "contents")
    private String contents;
    @Basic
    @Column(name = "createtime")
    private Timestamp createtime;
    @Basic
    @Column(name = "user_seq")
    private long userSeq;

    @OneToOne(mappedBy = "board", fetch = FetchType.LAZY)
    private DogInfo dogInfo;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<ImageStorage> imageStorageList = new ArrayList<>();
}
