package com.d209.mungtopia.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Info {
    private long userSeq;
    private String profile;
    private String username;
    private String nickname;
}
