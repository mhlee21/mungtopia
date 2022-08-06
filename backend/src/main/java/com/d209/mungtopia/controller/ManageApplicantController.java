package com.d209.mungtopia.controller;

import com.d209.mungtopia.common.ApiResponse;
import com.d209.mungtopia.repository.ManageApplicantRepository;
import com.d209.mungtopia.service.ManageApplicantService;
import com.d209.mungtopia.service.ManageApplicantServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manage/applicants")
@Api(value = "입양하기 - 입양인 API", tags = "Manage Applicant Controller - 입양하기(입양인) API")
@RequiredArgsConstructor
public class ManageApplicantController {

    private final ManageApplicantService manageApplicantService;

    //test
    private final  ManageApplicantRepository manageApplicantRepository;

    // 입양하기
    @GetMapping("/{user_id}")
    @ApiOperation(value = "mainInfo", notes = "")
    public ApiResponse mainInfo(@PathVariable("user_id")Long userId) {
        return ApiResponse.success("result", manageApplicantService.findApplicationList(userId));
    }

    //입양하기 상세
    @GetMapping("/detail/{user_id}")
    @ApiOperation(value = "", notes = "")
    public ApiResponse detailInfo() {
        return null;
    }

    //입양하기 상세 - 입양 절차
    @GetMapping("/process/{user_id}")
    @ApiOperation(value = "", notes = "")
    public ApiResponse processDetailInfo() {
        return null;
    }

    //입양 취소
    @DeleteMapping("/{adoption_process_id}")
    public ApiResponse cancleAdoption(@PathVariable("adoption_process_id") Long adoptionProcessId) {
        return null;
    }
}
