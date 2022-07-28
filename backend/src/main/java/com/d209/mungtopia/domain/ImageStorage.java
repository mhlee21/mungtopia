package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ImageStorage {
    @Id
    @GeneratedValue
    private Long imageStorageId;

    private Integer order;
    private String filename;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_storage_id")
    private Board board;

}
