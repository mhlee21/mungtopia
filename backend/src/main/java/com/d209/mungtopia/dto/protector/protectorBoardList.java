package com.d209.mungtopia.dto.protector;

import com.d209.mungtopia.entity.Application;
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
public class protectorBoardList {
    private Long boardId;
    private String dogImgUrl;
    private String dogName;
    private int adoptionStatus;
    private List<detailApplication> applicationList = new ArrayList<>();

    // mainInfo
    public protectorBoardList(Long boardId, String dogImgUrl, String dogName, int adoptionStatus) {
        this.boardId = boardId;
        this.dogImgUrl = dogImgUrl;
        this.dogName = dogName;
        this.adoptionStatus = adoptionStatus;
    }

    // detailInfo
    public protectorBoardList(Long boardId, String dogImgUrl, String dogName, int adoptionStatus, List<detailApplication> applicationList) {
        this.boardId = boardId;
        this.dogImgUrl = dogImgUrl;
        this.dogName = dogName;
        this.adoptionStatus = adoptionStatus;
        this.applicationList = applicationList;
    }
}
