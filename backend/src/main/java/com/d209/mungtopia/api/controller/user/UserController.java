package com.d209.mungtopia.api.controller.user;
import com.d209.mungtopia.api.entity.user.User;
import com.d209.mungtopia.api.service.UserService;
import com.d209.mungtopia.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // user 이름을 리턴해준다.
        User user = userService.getUser(principal.getUsername());

        return ApiResponse.success("user", user);
    }
}
