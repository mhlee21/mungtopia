package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.protector.ApplicantProcessDto;
import com.d209.mungtopia.dto.protector.ProtectorBoardListDto;
import com.d209.mungtopia.dto.protector.StepUpdateDto;

import java.util.List;

public interface ManageProtectorService {
    List<ProtectorBoardListDto> mainBoardInfo(Long userId);
    ProtectorBoardListDto detailApplicantInfo(Long boardId);
    List<ApplicantProcessDto> applicantDetailProcess(Long adoptionProcessId);
    Boolean deleteProcess(Long processId);
    List<ApplicantProcessDto> updateProcessStatus(Long adoptionProcessId, StepUpdateDto stepUpdateInfo);
}
