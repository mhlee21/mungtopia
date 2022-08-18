package com.d209.mungtopia.dto.manage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DateDto {
//    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private Timestamp date;
}
