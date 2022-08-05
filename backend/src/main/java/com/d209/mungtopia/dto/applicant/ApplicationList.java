package com.d209.mungtopia.dto.applicant;

import com.d209.mungtopia.entity.Application;
import com.d209.mungtopia.entity.ImageStorage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationList {

    private Long applicationId;

    private Boolean applicationStatus;

    private Long boardId;

    private String name; //dogName

    private ImageStorage imageStorage;

    //mainInfo
    public ApplicationList(Long applicationId, Boolean applicationStatus, Long boardId, String name, ImageStorage imageStorage) {
        this.applicationId = applicationId;
        this.applicationStatus = applicationStatus;
        this.boardId = boardId;
        this.name = name;
        this.imageStorage = imageStorage;
    }
}
