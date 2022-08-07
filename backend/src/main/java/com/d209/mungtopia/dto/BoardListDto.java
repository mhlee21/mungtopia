package com.d209.mungtopia.dto;

import com.d209.mungtopia.entity.DogInfo;
import com.d209.mungtopia.entity.ImageStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.sql.Timestamp;
import java.util.List;

public class BoardListDto {
    @Getter
    @AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 만듦
    public static class Response {
        private String boardTag;
        private Long boardId;
        private String dogName;
        private String contents;
        private Timestamp createtime;
        private boolean isLike;
        private Long likeCount;
        private Long commentCount;
        private DogInfo dogInfo;
        private List<ImageStorage> imageStorageList;
    }
}
