package com.d209.mungtopia.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserBoaordRes {
    List<Info> boardList = new ArrayList<>();
    List<Info> likeList = new ArrayList<>();
    List<Info> starList = new ArrayList<>();
}
