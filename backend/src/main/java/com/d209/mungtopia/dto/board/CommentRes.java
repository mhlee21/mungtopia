package com.d209.mungtopia.dto.board;


import com.d209.mungtopia.dto.user.Info;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRes {
    private long commentId;
    private Info author;
    private String contents;
    private boolean secret;
    private String createtime;
    private List<ReplyRes> replyList;
}
