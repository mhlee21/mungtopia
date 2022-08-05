package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.applicant.ApplicationList;
import com.d209.mungtopia.entity.Application;

import java.util.List;

public interface ManageApplicantService {
    List<ApplicationList> findApplicationList(Long userId);
}
