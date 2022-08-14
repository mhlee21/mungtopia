package com.d209.mungtopia.dto.board;

import com.d209.mungtopia.entity.Board;
import com.d209.mungtopia.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StarDto {
    private User user;
    private Board board;
}
