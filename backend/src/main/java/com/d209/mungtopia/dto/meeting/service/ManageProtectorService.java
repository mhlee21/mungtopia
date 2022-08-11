package com.d209.mungtopia.dto.meeting.service;

import com.d209.mungtopia.dto.protector.ApplicantProcessRes;
import com.d209.mungtopia.dto.protector.ProtectorBoardListRes;
import com.d209.mungtopia.dto.protector.StepRes;

import java.util.List;

public interface ManageProtectorService {
    List<ProtectorBoardListRes> mainBoardInfo(Long userId);
    ProtectorBoardListRes detailApplicantInfo(Long boardId);
    List<ApplicantProcessRes> applicantDetailProcess(Long adoptionProcessId);
    Boolean deleteProcess(Long processId);
    List<ApplicantProcessRes> updateProcessStatus(Long adoptionProcessId, StepRes stepUpdateInfo);
}
