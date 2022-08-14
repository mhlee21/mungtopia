package com.d209.mungtopia.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private boolean vaccination;
    private boolean adoptionStatus;
}
