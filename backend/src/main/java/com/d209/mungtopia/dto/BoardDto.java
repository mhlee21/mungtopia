package com.d209.mungtopia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private String contents;
    private Long userSeq;

    //ImageStorage
    List<ImageStorageDto> imageStorageDtoList;

    //DogInfo
    private String name;
    private String areaSido;
    private String areaGugun;
    private String gender;
    private String age;
    private String weight;
    private String breed;
    private Boolean vaccination;
    private Boolean neutering;
    private Boolean adoptionStatus;

    //DogNature
    private String nature1;
    private String nature2;
    private String nature3;
    private String nature4;
    private String nature5;
    private String nature6;
}
