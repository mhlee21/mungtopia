package com.d209.mungtopia.utils;

import com.d209.mungtopia.entity.ImageStorage;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class FileUtil {
    //날짜
    private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));




    // 랜덤 문자열 저장 - 파일 명으로 사용


    public List<ImageStorage> uploadFiles(MultipartFile[] files) throws IOException {
        // 파일이 비어있으면 비어있는 리스트 반환
        if (files[0].getSize() < 1){
            return Collections.emptyList();
        }

        List<ImageStorage> imgList = new ArrayList<>();

        int order = 1;



        return imgList;
    }
}
