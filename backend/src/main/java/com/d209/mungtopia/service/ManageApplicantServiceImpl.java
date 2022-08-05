package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.applicant.ApplicationList;
import com.d209.mungtopia.entity.Application;
import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.DogInfo;
import com.d209.mungtopia.entity.ImageStorage;
import com.d209.mungtopia.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageApplicantServiceImpl implements ManageApplicantService {

    private final ApplicationRepository applicationRepository;
    private final BoardRepository boardRepository;

    @Override
    public List<ApplicationList> findApplicationList(Long userId) {
        return null;
    }
}