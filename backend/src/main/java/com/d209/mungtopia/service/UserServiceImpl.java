package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.user.Info;
import com.d209.mungtopia.dto.user.UseInfoReq;
import com.d209.mungtopia.dto.user.UserBoardRes;
import com.d209.mungtopia.dto.user.UserBoardInfoRes;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.InfImageStorageRepository;
import com.d209.mungtopia.repository.InfUserRepository;
import com.d209.mungtopia.repository.user.UserRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
// RequiredArgsConstructor를 사용해서 생성자 주입 할 수 있다.
// final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성해주는 어노테이션
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final InfUserRepository infUserRepository;
    private final InfImageStorageRepository infImageStorageRepository;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final static String path = "https://i7d209.p.ssafy.io:8081/api/v1/image/";
    private final BoardService boardService;
    /**
     * 간단한 메인 정보 보내주기
     * @param userSeq
     * @return
     */
    @Override
    public Info getUser(long userSeq) {
        User user = infUserRepository.getReferenceById(userSeq);
        // user 생성 시 null처리
        // 이름 + 닉네임
        Info response = new Info(
                user.getUserSeq(),
                user.getProfileImageUrl(),
                user.getUsername(),
                user.getNickname()
        );
        return response;
    }

    /**
     * 개인정보 보내주기
     * @param userSeq
     * @return
     */
    @Override
    public UserInfo getDetailUser(long userSeq){
        User user = infUserRepository.getReferenceById(userSeq);
        UserInfo info = user.getUserInfo();
        UserInfo userInfo = new UserInfo(
                user.getUsername(),
                info.getGender(),
                info.getBirth(),
                info.getPhoneNumber(),
                info.getJob(),
                info.getZonecode(),
                info.getRoadAddress(),
                info.getDetailAddress(),
                info.getEtc()
        );
        return userInfo;
    }


    @Override
    @Transactional
    public Boolean putDetailUser(long userSeq, UseInfoReq inputUserInfo) {
        User user = infUserRepository.getReferenceById(userSeq);

        if (user == null)
            return false;

        UserInfo userInfo = user.getUserInfo();
        user.changeUserName(inputUserInfo.getName());
        userInfo.setGender(inputUserInfo.getGender());
        userInfo.setBirth(inputUserInfo.getBirth());
        userInfo.setPhoneNumber(inputUserInfo.getPhoneNumber());
        userInfo.setJob(inputUserInfo.getJob());
        userInfo.setZonecode(inputUserInfo.getZonecode());
        userInfo.setRoadAddress(inputUserInfo.getRoadAddress());
        userInfo.setDetailAddress(inputUserInfo.getDetailAddress());

        // 수정 저장
        infUserRepository.save(user);
        return true;
    }

    /**
     * 이미지 수정
     * @param multipartFile
     * @return
     */
    @Override
    @Transactional
    public byte[] putUserProfile(Long userSeq, MultipartFile multipartFile) throws IOException {
       String domin = "i7d209.p.ssafy.io";
        // ========= 이미지 서버 삭제 ========
        User user = infUserRepository.getReferenceById(userSeq);
        String profileImageUrl = user.getProfileImageUrl();
        // 기존에 우리 서버에 저장된 것만 서버에서 삭제해야함
        if (profileImageUrl.contains(domin)){
            System.out.println(" ================= file delete in!! ==========" );
            String[] split = profileImageUrl.split("/");
            String originSaveName = split[split.length - 1];
            System.out.println("======= originSaveName ======" + originSaveName);
            File file = new File("/var/images/" + originSaveName);
            if (file.exists()){
                System.out.println("=============== 파일 존재 =================");
                if (file.delete())
                    System.out.println("=============== 파일 삭제 성공 =================");
                else
                    System.out.println("=============== 파일 삭제 실패 =================");
            }else{
                System.out.println("=============== 파일이 존재하지 않음 =================");
            }
        }
        // ======== 이미지 서버 저장  =========
        System.out.println("=============== ...이미지 저장 시작... =================");
        if (multipartFile.isEmpty())
            return null;

        String root = System.getProperty("user.dir").toString() + "var/images";
        final String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        // 서버 저장 파일 명
        String today = new SimpleDateFormat("yyMMdd").format(new Date());
        final String saveName = getRandomString() + today + "." + extension;

        // 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성
        File target = new File(root , saveName);
        String uploadPath = target.getPath();
        System.out.println("uploadPath = " + uploadPath);

        try {
            multipartFile.transferTo(target); // 파일 저장
        } catch (IOException e) {
            throw new IOException(e);
        }

        // ======== 이미지 DB 저장  =========
        user.changeImg(path + saveName);
        infUserRepository.save(user);
        InputStream in = multipartFile.getInputStream();
        return IOUtils.toByteArray(in);
    }

    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    @Override
    public UserBoardRes getUserBoard(long userSeq) {
        User user = infUserRepository.getReferenceById(userSeq);
        List<Board> boardList = user.getBoardList();
        List<Likes> likesList = user.getLikesList();
        List<Star> starList = user.getStarList();

        UserBoardRes response = new UserBoardRes();
        for (Board board: boardList) {
            UserBoardInfoRes info = new UserBoardInfoRes(
                    board.getBoardId(),
                    infImageStorageRepository.findByBoardAndOrders(board, 1).getServerPath()
            );
            response.getBoardList().add(info);
        }

        for (Likes like: likesList) {
            Board board = like.getBoard();
            UserBoardInfoRes info = new UserBoardInfoRes(
                    board.getBoardId(),
                    infImageStorageRepository.findByBoardAndOrders(board, 1).getServerPath()
            );
            response.getLikeList().add(info);
        }

        for (Star star: starList) {
            Board board = star.getBoard();
            UserBoardInfoRes info = new UserBoardInfoRes(
                    board.getBoardId(),
                    infImageStorageRepository.findByBoardAndOrders(board, 1).getServerPath()
            );
            response.getStarList().add(info);
        }
        return response;
    }

    @Override
    public Info getUserSeq(String userId) {
        User user = infUserRepository.findByUserId(userId);
        Info info = new Info();
        info.setUserSeq(user.getUserSeq());
        info.setProfile(user.getProfileImageUrl());
        info.setUsername(user.getNickname()); // nickname으로 저장
        return info;
    }

    /**
     * 닉네임 변경
     * @param userSeq
     * @param name
     * @return
     */
    @Override
    @Transactional
    public String putUserNickName(long userSeq, String name) {
        Optional<User> user = infUserRepository.findById(userSeq);
        if (user.isEmpty())
            return null;
        user.get().changeNickName(name);
        infUserRepository.save(user.get());
        return name;
    }
}
