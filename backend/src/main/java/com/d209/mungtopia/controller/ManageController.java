package com.d209.mungtopia.controller;

import com.d209.mungtopia.common.ApiResponse;
import com.d209.mungtopia.service.ManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/v1/manage")
@Api(value = "입양 - 공통", tags = "Manage Controller - 입양공통 API")
@RequiredArgsConstructor
public class ManageController {

    private final ManageService manageService;

    @GetMapping("/{application_id}")
    @ApiOperation(value = "getApplication - 입양신청서 가져오기", notes = "제출한 입양신청서 확인")
    public ApiResponse getApplication(@PathVariable("application_id") Long appId) {
        return ApiResponse.success("data", manageService.getApplication(appId));
    }

    @GetMapping("/chat/{chat_room_id}")
    @ApiOperation(value = "getChatting - 채팅 가져오기", notes = "최신 순으로 채팅을 가져온다")
    public ApiResponse getChatting(@PathVariable("chat_room_id") Long chatRoomId,
                                   @RequestParam("pageNo") int pageNo, @RequestParam("role") String role) {

        return null;
    }

    @PostMapping("/chat/{chat_room_id}")
    @ApiOperation(value = "createChat - 채팅 저장", notes = "채팅을 저장한다")
    public ApiResponse createChat(@PathVariable("chat_room_id") Long chatRoomId) {
        return null;
    }

    @GetMapping("/schedule/{adoption_process_id}")
    @ApiOperation(value = "getSchedule - 일정 가져오기", notes = "현재 step의 일정을 가져온다")
    public ApiResponse getSchedule(@PathVariable("adoption_process_id") Long adoptionProcessId) {
        return null;
    }

    @PostMapping("/schedule/{adoption_process_id}")
    @ApiOperation(value = "postSchedule - 일정 수정", notes = "일정을 수정하거나 저장한다")
    public ApiResponse postSchedule(@PathVariable("adoption_process_id") Long adoptionProcessId, LocalDateTime date) {
        if (manageService.postSchedule(adoptionProcessId, date))
            return ApiResponse.success();
        else
            return ApiResponse.fail();
    }

    @DeleteMapping("/schedule/{adoption_process_id}/{step}")
    @ApiOperation(value = "deleteSchedule - 일정 삭제", notes = "기존에 있던 일정을 삭제한다")
    public ApiResponse deleteSchedule(@PathVariable("adoption_process_id") Long adoptionProcessId) {
        if (manageService.deleteSchedule(adoptionProcessId))
            return ApiResponse.success();
        else
            return ApiResponse.fail();
    }
}
