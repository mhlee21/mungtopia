package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "image_storage", schema = "mungtopia")
public class ImageStorage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "image_storage_id")
    private long imageStorageId;

    @Column(name = "orders")
    private int orders;

    @Column(name = "origin_filename")
    private String originFileName;

    @Column(name = "server_path")
    private String serverPath;

    @Column(name = "save_filename")
    private String saveFileName;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonManagedReference // 순환 참조 방지
    @JsonIgnore
    @JoinColumn(name = "board_id", referencedColumnName = "board_id")
    private Board board;

    public ImageStorage(int orders, String filename, String saveName){
        this.orders = orders;
        this.originFileName = filename;
        this.serverPath = saveName;
    }

    public void changeBoard(Board board){
        this.board = board;
    }
}
