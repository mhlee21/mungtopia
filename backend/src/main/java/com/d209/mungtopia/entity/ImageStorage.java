package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Table(name = "image_storage", schema = "mungtopia", catalog = "")
public class ImageStorage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "image_storage_id")
    private long imageStorageId;
    @Basic
    @Column(name = "orders")
    private Integer orders;
    @Basic
    @Column(name = "filename")
    private String filename;
    @Basic
    @Column(name = "origin_filename")
    private String originFilename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
    private Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
