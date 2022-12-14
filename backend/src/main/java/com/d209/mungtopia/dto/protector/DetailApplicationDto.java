package com.d209.mungtopia.dto.protector;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailApplicationDto {
    private Long userSeq;
    private Long applicationId;
    private String profileImg;
    private String username;
    private Long chatRoomId;
    private Integer applicationStatus;
    private Long adoptionProcessId;
}
