package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.applicant.ApplicationListInfoRes;
import com.d209.mungtopia.dto.applicant.DetailProcessRes;
import com.d209.mungtopia.dto.applicant.ProcessDetailRes;

import java.util.List;

public interface ManageApplicantService {
    List<ApplicationListInfoRes> mainApplicationInfo(Long userId);
    ProcessDetailRes detailProcressInfo(Long appId);
    DetailProcessRes processDetailInfo(Long appId);
    Boolean cancelAdoption(Long adoptionProcessId);
}
