package com.d209.mungtopia.repository.user;

import com.d209.mungtopia.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository - 생략 가능
@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    // 변수명을 카멜케이스로 맞춰야함 - findBy 적용이 안됨
    UserRefreshToken findByUserId(String userId);
    UserRefreshToken findByUserIdAndRefreshToken(String userId, String refreshToken);
    UserRefreshToken findByRefreshToken(String refreshToken);
}
