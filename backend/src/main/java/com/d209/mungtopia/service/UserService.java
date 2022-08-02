package com.d209.mungtopia.service;

import com.d209.mungtopia.entity.user.User;
import com.d209.mungtopia.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
// RequiredArgsConstructor를 사용해서 생성자 주입 할 수 있다.
// final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성해주는 어노테이션
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}
