package com.d209.mungtopia.dto.protector;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
/**
 * ManageProtectorController - mainInfo 사용
 *
 */
public class ProtectorBoardListDto {
    private Long boardId;
    private String dogImg;
    private String dogName;
    private int adoptionStatus;
    private List<DetailApplicationDto> applicationList = new ArrayList<>();

    // mainInfo
    public ProtectorBoardListDto(Long boardId, String dogImg, String dogName, int adoptionStatus) {
        this.boardId = boardId;
        this.dogImg = dogImg;
        this.dogName = dogName;
        this.adoptionStatus = adoptionStatus;
    }

    // detailInfo
    public ProtectorBoardListDto(Long boardId, String dogImg, String dogName, int adoptionStatus, List<DetailApplicationDto> applicationList) {
        this.boardId = boardId;
        this.dogImg = dogImg;
        this.dogName = dogName;
        this.adoptionStatus = adoptionStatus;
        this.applicationList = applicationList;
    }
}
