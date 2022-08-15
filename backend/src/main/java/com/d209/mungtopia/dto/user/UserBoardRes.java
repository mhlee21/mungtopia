package com.d209.mungtopia.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserBoardRes {
    List<UserBoardInfoRes> boardList = new ArrayList<>();
    List<UserBoardInfoRes> likeList = new ArrayList<>();
    List<UserBoardInfoRes> starList = new ArrayList<>();
}
