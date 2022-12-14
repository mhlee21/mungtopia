package com.d209.mungtopia.controller;

import com.d209.mungtopia.common.ApiResponse;
import com.d209.mungtopia.dto.protector.StepRes;
import com.d209.mungtopia.service.ManageProtectorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manage/protector")
@Api(value = "입양관리 - 보호자 API", tags = "Manage Protector Controller - 입양관리(보호자) API")
@RequiredArgsConstructor
public class ManageProtectorController {

    private final ManageProtectorService manageProtectorService;

    @GetMapping("/{user_seq}")
    @ApiOperation(value = "mainInfo - 입양보내기 메인", notes = "지금까지 올린 반려견의 리스트를 제공")
    public ApiResponse mainBoardInfo(@PathVariable(name = "user_seq") Long userId){
        return ApiResponse.success("data", manageProtectorService.mainBoardInfo(userId));
    }

    @GetMapping("/detail/{board_id}")
    @ApiOperation(value = "detailInfo - 입양 보내기 상세", notes = "입양글에 대한 신청자 리스트를 제공")
    public ApiResponse detailApplicantInfo(@PathVariable(name = "board_id") Long boardId){
        return ApiResponse.success("data", manageProtectorService.detailApplicantInfo(boardId));
    }

    @GetMapping("/detail/user/{adoption_process_id}")
    @ApiOperation(value = "processDetailInfo - 입양 신청자 상세 정보", notes = "입양 글에 대해 신청한 사람의 상세 정보를 제공")
    public ApiResponse applicantDetailProcess(@PathVariable(value = "adoption_process_id") Long adoptionProcessId){
        return ApiResponse.success("data", manageProtectorService.applicantDetailProcess(adoptionProcessId));
    }

    @DeleteMapping("/{adoption_process_id}")
    @ApiOperation(value = "deleteProcess - 입양 반려", notes = "신청자 입양 반려")
    public ApiResponse deleteProcess(@PathVariable("adoption_process_id") Long processId){
        Boolean result = manageProtectorService.deleteProcess(processId);
        if (result)
            return ApiResponse.success();
        else
            return ApiResponse.fail();
    }

    @PutMapping("/{adoption_process_id}")
    @ApiOperation(value = "updateProcessStatus - 입양 상태 변경", notes = "입양 절차에 따라 신청자의 입양 상태를 변경")
    public ApiResponse updateProcessStatus(@PathVariable("adoption_process_id") Long processId, @RequestBody StepRes stepUpdateInfo){
     return ApiResponse.success("data", manageProtectorService.updateProcessStatus(processId, stepUpdateInfo));
    }
}
