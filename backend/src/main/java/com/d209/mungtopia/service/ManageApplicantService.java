package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.applicant.ApplicationListInfoDto;
import com.d209.mungtopia.dto.applicant.DetailProcessDto;
import com.d209.mungtopia.dto.applicant.ProcessDetailDto;

import java.util.List;

public interface ManageApplicantService {
    List<ApplicationListInfoDto> mainApplicationInfo(Long userId);
    ProcessDetailDto detailProcressInfo(Long appId);
    DetailProcessDto processDetailInfo(Long appId);
    Boolean cancelAdoption(Long adoptionProcessId);
}
