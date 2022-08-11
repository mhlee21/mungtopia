package com.d209.mungtopia.controller;
import com.d209.mungtopia.entity.UserInfo;
import com.d209.mungtopia.dto.meeting.service.UserService;
import com.d209.mungtopia.common.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
// 사용자 정보를 가지고 오는 클래스
@Api(value = "회원", tags = "User Controller - 회원 API")
public class UserController {

    private final UserService userService;

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

//    public ApiResponse getUser() {
//        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        // user 이름을 리턴해준다.
//        User user = userService.getUser(principal.getUsername());
//
//        return ApiResponse.success("user", user);
//    }
}
