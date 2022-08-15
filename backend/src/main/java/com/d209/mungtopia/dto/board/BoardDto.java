package com.d209.mungtopia.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Long boardId;
    private Long userSeq;
    private String contents;
    private int boardTag;
    private Timestamp createtime;

    //ImageStorage
    List<ImageStorageDto> imageStorageDtoList;

    //DogInfo
    DogInfoDto dogInfoDto;

    //DogNature
    List<Integer> dogNature;
}
