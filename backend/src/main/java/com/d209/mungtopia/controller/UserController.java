package com.d209.mungtopia.controller;
import com.d209.mungtopia.entity.UserInfo;
import com.d209.mungtopia.service.UserService;
import com.d209.mungtopia.common.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import  org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
// 사용자 정보를 가지고 오는 클래스
@Api(value = "회원", tags = "User Controller - 회원 API")
public class UserController {

    private final UserService userService;

    @GetMapping()
    @ApiOperation(value = "getUser - 로그인 후 정보 보내주기", notes = "userSeq 가져오기")
    public ApiResponse getUserSeq() {
        log.debug("in!!!! userSeq");
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ApiResponse.success("user", userService.getUserSeq(principal.getUsername()));
    }

    @GetMapping("/{user_seq}")
    @ApiOperation(value = "getUser - 개인정보 가져오기", notes = "개인정보 가져오기")
    public ApiResponse getUser(@PathVariable("user_seq") long userSeq){
        return ApiResponse.success("data", userService.getUser(userSeq));
    }
    @PutMapping("/{user_seq}")
    @ApiOperation(value = "putUser - 개인정보 수정하기", notes = "개인정보 수정")
    public ApiResponse putUser(@PathVariable("user_seq") long userSeq, @RequestBody UserInfo userInfo){
        return ApiResponse.success("data", userService.putUser(userSeq, userInfo));
    }

    @GetMapping("/board/{user_seq}")
    @ApiOperation(value = "getUserBoard - 게시판 관련 유저 정보 가져오기", notes = "게시판 관련 유저 정보 가져오기")
    public ApiResponse getUserBoard(@PathVariable("user_seq") long userSeq){
        return ApiResponse.success("data", userService.getUserBoard(userSeq));
    }

}
