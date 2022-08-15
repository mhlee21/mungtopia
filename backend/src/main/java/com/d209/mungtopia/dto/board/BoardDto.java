package com.d209.mungtopia.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Long userSeq;
    private int boardTag;
    private String contents;
    private Long boardId;
    //ImageStorage
    //DogInfo
    List<ImageStorageDto> imageStorageDtoList;
    private Timestamp createtime;
    DogInfoDto dogInfo;
}
