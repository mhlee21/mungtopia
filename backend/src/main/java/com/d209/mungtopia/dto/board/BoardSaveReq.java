package com.d209.mungtopia.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardSaveReq {
    private BoardDto data;
    private List<MultipartFile> files;
}
