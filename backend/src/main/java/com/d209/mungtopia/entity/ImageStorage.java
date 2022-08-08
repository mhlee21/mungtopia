package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Table(name = "image_storage", schema = "mungtopia")
public class ImageStorage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "image_storage_id")
    private long imageStorageId;

    @Column(name = "orders")
    private int orders;

    @Column(name = "filename")
    private String filename;

    @Column(name = "origin_filename")
    private String originFilename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference // 순환 참조 방지
    @JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
    private Board board;
}
