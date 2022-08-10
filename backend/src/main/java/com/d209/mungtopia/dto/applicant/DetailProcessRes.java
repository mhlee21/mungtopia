package com.d209.mungtopia.dto.applicant;


import com.d209.mungtopia.dto.protector.ApplicantProcessRes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DetailProcessRes {
    Long adoptionProcessId;
    List<ApplicantProcessRes> applicantProcessRes = new ArrayList<>();
}
