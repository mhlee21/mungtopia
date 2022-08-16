package com.d209.mungtopia.controller;
import com.d209.mungtopia.dto.user.UseInfoReq;
import com.d209.mungtopia.dto.user.UserNicknameRes;
import com.d209.mungtopia.entity.UserInfo;
import com.d209.mungtopia.service.UserService;
import com.d209.mungtopia.common.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
// 사용자 정보를 가지고 오는 클래스
@Api(value = "회원", tags = "User Controller - 회원 API")
public class UserController {

    private final UserService userService;

    /**
     * 로그인 후 userSeq 전달
     * @return
     */
    @GetMapping
    @ApiOperation(value = "getUser - 로그인 후 정보 보내주기", notes = "userSeq 가져오기")
    public ApiResponse getUserSeq() {
        System.out.println("\"ddddd\" = " + "login in!!!!");
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ApiResponse.success("user", userService.getUserSeq(principal.getUsername()));
    }

    /**
     *  메인 개인정보
     * @param userSeq
     * @return
     */
    @GetMapping("/{user_seq}")
    @ApiOperation(value = "getUser - 개인정보 가져오기", notes = "개인정보 가져오기")
    public ApiResponse getUser(@PathVariable("user_seq") long userSeq){
        return ApiResponse.success("data", userService.getUser(userSeq));
    }

    /**
     * 유저 프로필 이미지 수정
     */
    @PostMapping("/{user_seq}")
    public ApiResponse putUserProfile(@PathVariable("user_seq") Long userSeq,
                                      @RequestParam("files") MultipartFile multipartFile) throws IOException {
//        System.out.println("multipartFile = " + multipartFile.isEmpty());
        return ApiResponse.success("data", userService.putUserProfile(userSeq, multipartFile));
//        return ApiResponse.success();
    }

    /**
     * 유저 상세정보 가져오기
     */
    @GetMapping("/detail/{user_seq}")
    @ApiOperation(value = "getUser - 개인정보 가져오기", notes = "개인정보 가져오기")
    public ApiResponse getDetailUser(@PathVariable("user_seq") long userSeq){
        return ApiResponse.success("data", userService.getDetailUser(userSeq));
    }

    /**
     * 유저 상세 정보 수정
     * @param userSeq
     * @param userInfo
     * @return
     */
    @PutMapping("/detail/{user_seq}")
    @ApiOperation(value = "putUser - 개인정보 수정하기", notes = "개인정보 수정")
    public ApiResponse putDetailUser(@PathVariable("user_seq") long userSeq, @RequestBody UseInfoReq userInfo){
        return ApiResponse.success("data", userService.putDetailUser(userSeq, userInfo));
    }

    /**
     * 유저 게시판 정보 가져오기
     * @param userSeq
     * @return
     */
    @GetMapping("/board/{user_seq}")
    @ApiOperation(value = "getUserBoard - 게시판 관련 유저 정보 가져오기", notes = "게시판 관련 유저 정보 가져오기")
    public ApiResponse getUserBoard(@PathVariable("user_seq") long userSeq){
        return ApiResponse.success("data", userService.getUserBoard(userSeq));
    }

    @PutMapping("/name/{userSeq}")
    public ApiResponse putUserNickName(@PathVariable Long userSeq, @RequestBody UserNicknameRes nickname){
        return ApiResponse.success("data", userService.putUserNickName(userSeq, nickname.getNickname()));
    }
}
