package com.d209.mungtopia.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DogInfoDto {
    private Long dogInfoId;
    private String name;
    private String areaSido;
    private String areaGugun;
    private String gender;
    private String age;
    private String weight;
    private String breed;
    private boolean neutering; // 중성화 여부
    //DogNature
    List<Integer> dogNature;
    private boolean vaccination;
    private boolean adoptionStatus;

    public DogInfoDto(Long dogInfoId,
                      String name,
                      String areaSido,
                      String areaGugun,
                      String gender,
                      String age,
                      String weight,
                      String breed,
                      boolean neutering,
                      boolean vaccination,
                      boolean adoptionStatus) {
        this.dogInfoId = dogInfoId;
        this.name = name;
        this.areaSido = areaSido;
        this.areaGugun = areaGugun;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
        this.neutering = neutering;
        this.vaccination = vaccination;
        this.adoptionStatus = adoptionStatus;
    }
}
