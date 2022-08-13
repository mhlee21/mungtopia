package com.d209.mungtopia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageStorageDto {
    private int orders;
    private String fileName;
    private String saveName;
    private long boardId;
}
