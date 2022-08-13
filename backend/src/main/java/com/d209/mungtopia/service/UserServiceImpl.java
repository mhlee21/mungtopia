package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.user.Info;
import com.d209.mungtopia.dto.user.UserBoaordRes;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.InfImageStorageRepository;
import com.d209.mungtopia.repository.InfUserRepository;
import com.d209.mungtopia.repository.user.UserRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// RequiredArgsConstructor를 사용해서 생성자 주입 할 수 있다.
// final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성해주는 어노테이션
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final InfUserRepository infUserRepository;
    private final InfImageStorageRepository infImageStorageRepository;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    @Override
    public UserInfo getUser(long userSeq) {
        User user = infUserRepository.getReferenceById(userSeq);
        // user 생성 시 null처리
        UserInfo response = new UserInfo(
                user.getUserInfo().getPhoneNumber(),
                user.getUserInfo().getBirth(),
                user.getUserInfo().getGender(),
                user.getUserInfo().getJob(),
                user.getUserInfo().getZonecode(),
                user.getUserInfo().getRoadAddress(),
                user.getUserInfo().getDetailAddress(),
                user.getUserInfo().getEtc()
        );
        response.setInfo(user.getNickname(), user.getProfileImageUrl(), user.getEmail(), user.getUsername());
        return response;
    }

    @Override
    public Boolean putUser(long userSeq, UserInfo userInfo) {
        User user = infUserRepository.getReferenceById(userSeq);
        if (user == null)
            return false;

        user.changeUser(userInfo,
                userInfo.getNickname(),
                userInfo.getProfile());

        infUserRepository.save(user);
        return true;
    }

    @Override
    public UserBoaordRes getUserBoard(long userSeq) {
        User user = infUserRepository.getReferenceById(userSeq);
        List<Board> boardList = user.getBoardList();
        List<Likes> likesList = user.getLikesList();
        List<Star> starList = user.getStarList();

        UserBoaordRes response = new UserBoaordRes();
        for (Board board: boardList) {
            Info info = new Info();
            info.setUserId(board.getBoardId());
            info.setProfile(infImageStorageRepository.findByBoardAndOrders(board, 1).getFilename());
            response.getBoardList().add(info);
        }

        for (Likes like: likesList) {
            Board board = like.getBoard();
            Info info = new Info();
            info.setUserId(board.getBoardId());
            info.setProfile(infImageStorageRepository.findByBoardAndOrders(board, 1).getFilename());
            response.getLikeList().add(info);
        }

        for (Star star: starList) {
            Board board = star.getBoard();
            Info info = new Info();
            info.setUserId(board.getBoardId());
            info.setProfile(infImageStorageRepository.findByBoardAndOrders(board, 1).getFilename());
            response.getStarList().add(info);
        }
        return response;
    }

    @Override
    public Info getUserSeq(String token) {
        UserRefreshToken refreshToken = userRefreshTokenRepository.findByRefreshToken(token);
        String userId = refreshToken.getUserId();
        User user = infUserRepository.findByUserId(userId);
        Info info = new Info();
        info.setUserId(user.getUserSeq());
        info.setProfile(user.getProfileImageUrl());
        info.setUsername(user.getNickname()); // nickname으로 저장
        return info;
    }
}
