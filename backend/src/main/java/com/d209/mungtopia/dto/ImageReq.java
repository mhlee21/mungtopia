package com.d209.mungtopia.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ImageReq {
    private String board_id;
    private List<MultipartFile> files;
}
