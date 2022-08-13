package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.user.Info;
import com.d209.mungtopia.dto.user.UserBoaordRes;
import com.d209.mungtopia.entity.UserInfo;

public interface UserService {
    UserInfo getUser(long userSeq);
    Boolean putUser (long userSeq, UserInfo userInfo);
    UserBoaordRes getUserBoard(long userSeq);
    Info getUserSeq(String token);
}
