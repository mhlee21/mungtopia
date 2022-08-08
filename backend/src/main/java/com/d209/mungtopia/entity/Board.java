package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "board", schema = "mungtopia")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "board_tag")
    private String boardTag;

    @Column(name = "contents")
    private String contents;

    @Column(name = "createtime")
    private Timestamp createtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @OneToOne(mappedBy = "board", fetch = FetchType.LAZY)
    private DogInfo dogInfo;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonBackReference //순환 참조 방지
    private List<ImageStorage> imageStorageList = new ArrayList<>();
}
