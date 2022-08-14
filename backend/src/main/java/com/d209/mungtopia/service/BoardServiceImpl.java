package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.*;
import com.d209.mungtopia.dto.board.DogInfoDto;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.*;
import com.d209.mungtopia.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final static String[] boardTag = {"입양", "후기", "잡담"};
    private final static Map<String, Integer> boardTagMap = Map.of(
            "입양", 1,
            "후기", 2,
            "잡담", 3
    );
    private final static String path = "https://i7d209.p.ssafy.io:8080/api/v1/image/";
    private final UserRepository userRepository;
    private final InfUserRepository infUserRepository;
    private final InfBoardRepository boardRepository;
    private final InfLikeRepository likeRepository;
    private final InfStarRepository starRepository;
    private final InfCommentRepository commentRepository;
    private final InfReplyRepository replyRepository;
    private final InfDogInfoRepository dogInfoRepository;
    private final InfDogNatureRepository dogNatureRepository;
    private final InfImageStorageRepository imageStorageRepository;
    private final InfApplicationRepository applicationRepository;
    private final InfAnswerRepository answerRepository;
    private final InfUserDogNatureRepository infUserDogNatureRepository;

    public Timestamp getNow() {
        return new Timestamp(System.currentTimeMillis());
    }

    @Override
    public List<BoardListDto.Response> findBoardAll(int tagNo, int pageNo, long userSeq) {
        /*
         * 전체 : 0
         * 입양 : 1 (추천하는 강아지 글 3개 먼저 보여주고 나머지는 최신순으로 보여줌
         * 후기 : 2
         * 자유 : 3
         */
        List<BoardListDto.Response> response = new ArrayList<>();
        if (tagNo == 0){ // 전체
            // 전체 boardList 최신순으로 가져오기
            Pageable pageRequest = PageRequest.of(pageNo, 15, Sort.by("createtime").descending());
            Page<Board> boardList = boardRepository.findAll(pageRequest);
            for (Board board: boardList) {
                BoardListDto.Response result = sameInfoLogic(board, userSeq);
                if (board.getBoardTag().equals("입양")){
                    DogInfo dogInfo = board.getDogInfo();
                    result.setDogName(dogInfo.getName());
                    result.setDogInfo(setDogInfoDto(dogInfo));
                }
                response.add(result);
            }
        }else if (tagNo == 1){ // 입양
            Pageable pageRequest = PageRequest.of(pageNo, 15, Sort.by("createtime").descending());
            // 매칭 로직
            Page<Board> boardList = null;
            if(userSeq != 0){ // 입양 해줌
                User user = userRepository.getReferenceById(userSeq);
                Optional<UserDogNature> userNature = infUserDogNatureRepository.findByUser(user);
                System.out.println("userNature.isPresent() = " + userNature.isPresent());
                if (userNature.isPresent()){ // userNature가 존재하면
                    List<Long> matching = new ArrayList<>();
                    List<DogNature>  dogNatureList = dogNatureRepository.findAll();
                    Map<Long, Integer> result = new HashMap<>();
                    for (int i = 0; i < dogNatureList.size(); i++) {
                        DogNature dogNature = dogNatureList.get(i);
                        long boardId = dogNature.getDogInfo().getBoard().getBoardId();
                        int sum = 0;
                        sum += Math.abs(userNature.get().getNature1() - dogNature.getNature1() * 3);
                        sum += Math.abs(userNature.get().getNature2() - dogNature.getNature2() * 3);
                        sum += Math.abs(userNature.get().getNature3() - dogNature.getNature3() * 3);
                        sum += Math.abs(userNature.get().getNature4() - dogNature.getNature4() * 3);
                        sum += Math.abs(userNature.get().getNature5() - dogNature.getNature5() * 3);
                        sum += Math.abs(userNature.get().getNature6() - dogNature.getNature6() * 3);

                        result.put(boardId, sum); // 결과 저장
                    }
                    // 정렬
                    List<Map.Entry<Long, Integer>> resultList = new LinkedList<>(result.entrySet());
                    resultList.sort(Map.Entry.comparingByValue());
                    for (int i = 0; i < resultList.size(); i++) {
                        if (i == 3)
                            break;
                       matching.add(resultList.get(i).getKey());
                    }
                    System.out.println("matching = " + matching);

                    for (Long boardId: matching) {
                        Board board = boardRepository.getById(boardId);
                        BoardListDto.Response matchingResult = sameInfoLogic(board, userSeq);

                        DogInfo dogInfo = board.getDogInfo();
                        matchingResult.setDogName(dogInfo.getName());
                        matchingResult.setDogInfo(setDogInfoDto(dogInfo));
                        matchingResult.setRecommend(true);
                        response.add(matchingResult);
                    }
                    if (pageNo == 0)
                        pageRequest = PageRequest.of(pageNo, 15 - matching.size(), Sort.by("createtime").descending());
                    boardList = boardRepository.findByBoardIdNotInAndBoardTagOrderByCreatetimeDesc(matching, "입양", pageRequest);
                }else{
                    boardList = boardRepository.findByBoardTagOrderByCreatetimeDesc(boardTag[tagNo - 1], pageRequest); // 그냥 가져오기
                }
            }else{ // 입양 상관 없이 그냥 뽑음
                boardList = boardRepository.findByBoardTagOrderByCreatetimeDesc(boardTag[tagNo - 1], pageRequest);
            }

            for (Board board: boardList) {
                BoardListDto.Response result = sameInfoLogic(board, userSeq);
                DogInfo dogInfo = board.getDogInfo();
                result.setDogName(dogInfo.getName());
                result.setDogInfo(setDogInfoDto(dogInfo));
                result.setRecommend(false);
                response.add(result);
            }
            return response;
        }else if (tagNo == 2 || tagNo == 3){ // 후기
            Pageable pageRequest = PageRequest.of(pageNo, 15, Sort.by("createtime").descending());
            Page<Board> boardList = boardRepository.findByBoardTagOrderByCreatetimeDesc(boardTag[tagNo - 1], pageRequest);

            for (Board board: boardList) {
                response.add(sameInfoLogic(board, userSeq));
            }
        }
        return response;
    }
    private DogInfoDto setDogInfoDto(DogInfo dogInfo){
        DogInfoDto dto = new DogInfoDto(
            dogInfo.getDogInfoId(),
                dogInfo.getName(),
                dogInfo.getAreaSido(),
                dogInfo.getAreaGugun(),
                dogInfo.getGender(),
                dogInfo.getAge(),
                dogInfo.getWeight(),
                dogInfo.getBreed(),
                dogInfo.getVaccination(),
                dogInfo.getAdoptionStatus()
        );
        return dto;
    }
    private BoardListDto.Response sameInfoLogic(Board board, Long userSeq){
        User author = board.getUser();
        BoardListDto.Response result = new BoardListDto.Response();
        result.setBoardId(board.getBoardId());
        result.setBoardTag(boardTagMap.get(board.getBoardTag().trim()));

        result.setContents(board.getContents());
        result.setCreatetime(board.getCreatetime().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        if (userSeq != 0){
            User user = infUserRepository.getReferenceById(userSeq);
            Optional<Likes> likes = likeRepository.findLikesByUserAndBoard(user, board);
            Optional<Star> stars = starRepository.findStarByUserAndBoard(user, board);

            result.setLike(likes.isPresent());
            result.setStar(stars.isPresent());
        }else{
            result.setLike(false);
            result.setStar(false);
        }
        Long likeCount = likeRepository.countByBoard(board);
        Long starCount = starRepository.countByBoard(board);
        Long commentCount = commentRepository.countByBoard(board);

        result.setLikeCount(likeCount);
        result.setStarCount(starCount);
        result.setCommentCount(commentCount);

        result.setUsername(author.getNickname()); // 닉네임 저장 - 게시판
        result.setProfile(author.getProfileImageUrl());

        List<ImageStorage> imageStorageList = imageStorageRepository.findByBoardOrderByOrders(board);
        for (ImageStorage img: imageStorageList) {
            result.getImageStorageList().add(img.getServerPath());
        }
        return result;
    }

    @Override
    public List<Board> search(Long tagNo, int pageNo, String keyword) {
        List<Board> boardList = boardRepository.findAllByBoardTag(boardTag[tagNo.intValue() - 1], keyword);
        return boardList;
    }

    @Override
    public Board saveBoard(Long tagNo, BoardDto boardDto) {
        User user = userRepository.findById(boardDto.getUserSeq()).get();

        Board board = Board.builder()
                .boardTag(boardTag[tagNo.intValue() - 1])
                .contents(boardDto.getContents())
                .createtime(getNow())
                .user(user)
                .build();
        boardRepository.save(board);

        List<ImageStorage> imageStorageList = new ArrayList<>();
        for (ImageStorageDto imageStorageDto : boardDto.getImageStorageDtoList()) {
            ImageStorage imageStorage = ImageStorage.builder()
                    .orders(imageStorageDto.getOrders())
                    .originFileName(imageStorageDto.getServerPath())
                    .board(board)
                    .build();
            imageStorageRepository.save(imageStorage);
            imageStorageList.add(imageStorage);
        }
        board.setImageStorageList(imageStorageList);

        if (tagNo == 1) { // 입양
            DogInfo dogInfo = DogInfo.builder()
                    .board(board)
                    .name(boardDto.getName())
                    .areaSido(boardDto.getAreaSido())
                    .areaGugun(boardDto.getAreaGugun())
                    .gender(boardDto.getGender())
                    .age(boardDto.getAge())
                    .weight(boardDto.getWeight())
                    .breed(boardDto.getBreed())
                    .vaccination(boardDto.getVaccination())
                    .neutering(boardDto.getNeutering())
                    .adoptionStatus(boardDto.getAdoptionStatus())
                    .build();
            dogInfoRepository.save(dogInfo);
            board.setDogInfo(dogInfo);

            DogNature dogNature = DogNature.builder()
                    .dogInfo(dogInfo)
                    .nature1(boardDto.getNature1())
                    .nature2(boardDto.getNature2())
                    .nature3(boardDto.getNature3())
                    .nature4(boardDto.getNature4())
                    .nature5(boardDto.getNature5())
                    .nature6(boardDto.getNature6())
                    .build();
            dogNatureRepository.save(dogNature);
            dogInfo.setDogNature(dogNature);
        }
        return board;
    }

    @Override
    public Board updateBoard(Board board, BoardDto boardDto) {
        //Contents 수정
        board.setContents(boardDto.getContents());

        //image 수정
        List<ImageStorage> imageStorageList = board.getImageStorageList();
        if (!imageStorageList.isEmpty()) {
            //기존 이미지 모두 지우기
            for (ImageStorage imageStorage : imageStorageList) {
                imageStorageRepository.delete(imageStorage);
            }
        }

        //이미지 새로 저장하기
        List<ImageStorage> newImageStorageList = new ArrayList<>();
        for (ImageStorageDto imageStorageDto : boardDto.getImageStorageDtoList()) {
            ImageStorage imageStorage = ImageStorage.builder()
                    .orders(imageStorageDto.getOrders())
                    .originFileName(imageStorageDto.getServerPath())
                    .board(board)
                    .build();
            newImageStorageList.add(imageStorage);
        }
        board.setImageStorageList(newImageStorageList);

        //DogInfo 수정
        DogInfo dogInfo = board.getDogInfo();
        dogInfo.setName(boardDto.getName());
        dogInfo.setAreaSido(boardDto.getAreaSido());
        dogInfo.setAreaGugun(boardDto.getAreaGugun());
        dogInfo.setGender(boardDto.getGender());
        dogInfo.setAge(boardDto.getAge());
        dogInfo.setWeight(boardDto.getWeight());
        dogInfo.setBreed(boardDto.getBreed());
        dogInfo.setVaccination(boardDto.getVaccination());
        dogInfo.setNeutering(boardDto.getNeutering());
        dogInfo.setAdoptionStatus(boardDto.getAdoptionStatus());
        dogInfoRepository.save(dogInfo);

        //DogNature 수정
        DogNature dogNature = dogInfo.getDogNature();
        dogNature.setNature1(boardDto.getNature1());
        dogNature.setNature2(boardDto.getNature2());
        dogNature.setNature3(boardDto.getNature3());
        dogNature.setNature4(boardDto.getNature4());
        dogNature.setNature5(boardDto.getNature5());
        dogNature.setNature6(boardDto.getNature6());
        dogNatureRepository.save(dogNature);

        return board;
    }

    @Override
    public Boolean deleteBoard(Board board) {
        boardRepository.delete(board);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Board findBoardDetail(Long boardId) {
        return boardRepository.findById(boardId).get();
    }

    @Override
    public Application saveApplication(Board board, AppDto appDto) {
        User user = userRepository.findById(appDto.getUserSeq()).get();
        Application application = Application.builder()
                .boardId(board.getBoardId())
                .send(true)
                .name(user.getUsername())
                .userInfo(user.getUserInfo())
                .user(user)
                .createtime(LocalDateTime.now())
                .applicationStatus(0)
                .build();

        List<Answer> answerList = new ArrayList<>();
        for (AnswerDto answerDto : appDto.getApplicantAnswerList()) {
            Answer answer = new Answer(answerDto.getIdx().intValue(),
                    answerDto.getAnswer(),
                    application);
            answerRepository.save(answer);
            answerList.add(answer);
        }

        application.setAnswerList(answerList);
        applicationRepository.save(application);

        return application;
    }

    @Override
    public Boolean likes(User user, Board board) {
        LikesDto likesDto = new LikesDto(user, board);

        //이미 좋아요 한 board 인 경우 409 에러
        if (likeRepository.findLikesByUserAndBoard(user, board).isPresent()) {
            return false;
        }

        Likes likes = Likes.builder() //롬복의 @Builder 어노테이션 사용
                .createtime(getNow())
                .user(user)
                .board(board)
                .build();
        likeRepository.save(likes);

        return true;
    }

    @Override
    public Boolean unlikes(User user, Board board) {
        Optional<Likes> likes = likeRepository.findLikesByUserAndBoard(user, board);
        if (likes.isEmpty()) {
            return false;
        }

        likeRepository.delete(likes.get());
        return true;
    }

    @Override
    public Boolean star(User user, Board board) {
        StarDto starDto = new StarDto(user, board);

        //이미 좋아요 한 board 인 경우 409 에러
        if (starRepository.findStarByUserAndBoard(user, board).isPresent()) {
            return false;
        }

        Star star = Star.builder() //롬복의 @Builder 어노테이션 사용
                .createtime(getNow())
                .user(user)
                .board(board)
                .build();
        starRepository.save(star);
        return true;
    }

    @Override
    public Boolean unstar(User user, Board board) {
        Optional<Star> star = starRepository.findStarByUserAndBoard(user, board);
        if (star.isEmpty()) {
            return false;
        }

        starRepository.delete(star.get());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> CommentAll(Board board) {
        return commentRepository.findByBoard(board);
    }

    public List<Comment> saveComment(Board board, CommentDto commentDto) {
        Comment comment = Comment.builder()
                .userSeq(commentDto.getUserSeq())
                .userNickname(commentDto.getUserNickname())
                .contents(commentDto.getContents())
                .secret(commentDto.isSecret())
                .createtime(getNow())
                .board(board)
                .build();
        commentRepository.save(comment);
        return commentRepository.findByBoard(board);
    }

    @Override
    public List<Comment> updateComment(Board board, Comment comment, CommentDto commentDto) {
        comment.setContents(commentDto.getContents());
        comment.setSecret(commentDto.isSecret());
//        comment.setCreatetime(getNow());
        commentRepository.save(comment);
        return commentRepository.findByBoard(board);
    }


    @Override
    public List<Comment> deleteComment(Board board, Comment comment, CommentDto commentDto) {
        commentRepository.delete(comment);
        return commentRepository.findByBoard(board);
    }

    @Override
    public List<Comment> saveReply(Board board, Comment comment, ReplyDto replyDto) {
        Reply reply = Reply.builder()
                .userSeq(replyDto.getUserSeq())
                .userNickname(replyDto.getUserNickname())
                .content(replyDto.getContent())
                .secret(replyDto.isSecret())
                .createtime(getNow())
                .comment(comment)
                .build();
        replyRepository.save(reply);
        return commentRepository.findByBoard(board);
    }

    @Override
    public List<Comment> updateReply(Board board, Reply reply, ReplyDto replyDto) {
        reply.setContent(replyDto.getContent());
        reply.setSecret(replyDto.isSecret());
//        reply.setCreatetime(getNow());
        replyRepository.save(reply);
        return commentRepository.findByBoard(board);
    }

    @Override
    public List<Comment> deleteReply(Board board, Reply reply, ReplyDto replyDto) {
        replyRepository.delete(reply);
        return commentRepository.findByBoard(board);
    }

    @Override
    @Transactional
    public Boolean saveImgFile(List<MultipartFile> multipartFiles, long boardId) throws Exception {
        // 비어있으면 false
        if (multipartFiles.isEmpty())
            return false;

        // 파일 정보를 담을 수 있는 리스트
        List<ImageStorage> imageStorageDtoList = new ArrayList<>();

        // 서버에서 / 로 출력
        String root = System.getProperty("user.dir").toString() + "var/images";
        System.out.println("root = " + root);

        // 파일 저장
        Path path = Paths.get("var", "images");
        System.out.println("path = " + path);
        int order = 1;


        File dir = new File(root);
        if (!dir.exists()){
            // 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리 생성
            dir.mkdir();
        }

        // 파일 개수 만큼 forEach
        for (MultipartFile file : multipartFiles) {
            if (file.isEmpty()) // 파일이 비어있으면 false;
                return false;

            // 파일 확장자
            final String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            // 서버 저장 파일 명
            String today = new SimpleDateFormat("yyMMdd").format(new Date());
            final String saveName = getRandomString() + today + "." + extension;

            // 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성
            File target = new File(root , saveName);
            String uploadPath = target.getPath();
            System.out.println("uploadPath = " + uploadPath);


            try {
                file.transferTo(target); // 파일 저장
            } catch (IOException e) {
                throw new IOException(e);
            }

            // JPA 저장 - 수정 로직 필요?
            ImageStorage imageStorage = new ImageStorage(order, file.getOriginalFilename(), uploadPath);
            imageStorageDtoList.add(imageStorage);

            order++;
        }

//         파일 정보 DB 저장
        Optional<Board> board = boardRepository.findById(boardId);
        if (board.isEmpty())
            return false;

        for (ImageStorage img : imageStorageDtoList) {
            img.changeBoard(board.get());
            imageStorageRepository.save(img);
        }

        return true;
    }

    @Override
    public Resource getImgFile(long boardId, int order) throws IOException {
//        String root = System.getProperty("user.dir").toString() + "var/images";

        Optional<Board> board = boardRepository.findById(boardId);
        ImageStorage img = imageStorageRepository.findByBoardAndOrders(board.get(), order);
        String saveName = img.getServerPath();

        try{
            Resource urlResource = new FileUrlResource( saveName);
            if (urlResource.exists() || urlResource.isReadable()){
                System.out.println("============= urlResource in!!! ============= ");
                return urlResource;
            }
            else
                throw new FileNotFoundException("could not find file!!");
        }catch (MalformedURLException e){
            throw  new FileNotFoundException("Could not download file");
        }
    }

    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
