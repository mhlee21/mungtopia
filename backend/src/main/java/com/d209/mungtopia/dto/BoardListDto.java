package com.d209.mungtopia.dto;

import com.d209.mungtopia.dto.board.DogInfoDto;
import com.d209.mungtopia.entity.DogInfo;
import com.d209.mungtopia.entity.ImageStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BoardListDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 만듦
    public static class Response {
        private int boardTag;
        private Long boardId;

        private boolean recommend;
        private String dogName;
        private String contents;
        private String createtime;
        private boolean isLike;
        private boolean isStar;
        private Long likeCount;
        private Long StarCount;
        private Long commentCount;

        // nickname
        private String username;
        private String profile;

        private DogInfoDto dogInfo;
        private List<String> imageStorageList = new ArrayList<>();

    public List<String> getImageStorageList() {
        return imageStorageList;
    }
}
}
