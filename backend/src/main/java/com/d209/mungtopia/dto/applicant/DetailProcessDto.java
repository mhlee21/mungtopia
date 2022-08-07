package com.d209.mungtopia.dto.applicant;


import com.d209.mungtopia.dto.protector.ApplicantProcessDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DetailProcessDto {
    Long adoptionProcessId;
    List<ApplicantProcessDto> applicantProcessDto;
}
