package com.d209.mungtopia.dto.board;

import com.d209.mungtopia.dto.user.Info;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRes {
    private Long replyId;
    private Info author;
    private String contents;
    private boolean secret;
    private String createtime;
}
