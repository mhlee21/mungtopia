package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.protector.protectorBoardList;

import java.util.List;

public interface ManageProtectorService {
    List<protectorBoardList> mainBoardInfo(Long userId);
    protectorBoardList detailApplicantInfo(Long boardId);
}
