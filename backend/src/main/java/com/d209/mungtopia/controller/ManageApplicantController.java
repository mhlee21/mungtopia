package com.d209.mungtopia.controller;

import com.d209.mungtopia.common.ApiResponse;
import com.d209.mungtopia.service.ManageApplicantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manage/applicants")
@Api(value = "입양하기 - 입양인 API", tags = "Manage Applicant Controller - 입양하기(입양인) API")
@RequiredArgsConstructor
public class ManageApplicantController {

    private final ManageApplicantService manageApplicantService;

    @GetMapping("/{user_id}")
    @ApiOperation(value = "mainApplicationInfo - 입양하기 메인", notes = "지금까지 제출한 입양 신청서 리스트")
    public ApiResponse mainApplicationInfo(@PathVariable("user_id")Long userId) {
        return ApiResponse.success("data", manageApplicantService.mainApplicationInfo(userId));
    }

    @GetMapping("/detail/{application_id}")
    @ApiOperation(value = "detailApplicationInfo - 입양하기 상세(입양 관련 내용)", notes = "")
    public ApiResponse detailProcressInfo(@PathVariable("application_id") Long appId) {
        return ApiResponse.success("data", manageApplicantService.detailProcressInfo(appId));
    }

    //입양하기 상세 - 입양 절차
    @GetMapping("/process/{application_id}")
    @ApiOperation(value = "processDetailInfo - 입양하기 상세(입양 절차)", notes = "")
    public ApiResponse processDetailInfo(@PathVariable("application_id") Long appId) {
        return ApiResponse.success("data", manageApplicantService.processDetailInfo(appId));
    }

    //입양 취소
    @DeleteMapping("/{adoption_process_id}")
    public ApiResponse cancleAdoption(@PathVariable("adoption_process_id") Long adoptionProcessId) {
        if (manageApplicantService.cancelAdoption(adoptionProcessId))
            return ApiResponse.success();
        else
            return ApiResponse.fail();
    }
}
