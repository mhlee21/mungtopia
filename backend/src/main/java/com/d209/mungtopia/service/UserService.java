package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.user.Info;
import com.d209.mungtopia.dto.user.UseInfoReq;
import com.d209.mungtopia.dto.user.UserBoardRes;
import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    Info getUser(long userSeq);
    Boolean putDetailUser (long userSeq, UseInfoReq userInfo);
    String putUserProfile(Long userSeq, MultipartFile multipartFile) throws IOException;
    UserBoardRes getUserBoard(long userSeq);
    UserInfo getDetailUser(long userSeq);
    Info getUserSeq(String token);
    String putUserNickName(long userSeq, String name);
}
