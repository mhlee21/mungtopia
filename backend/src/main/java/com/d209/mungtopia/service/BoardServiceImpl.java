package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.applicant.AnswerDto;
import com.d209.mungtopia.dto.board.DogInfoDto;
import com.d209.mungtopia.dto.applicant.AppDto;
import com.d209.mungtopia.dto.board.*;
import com.d209.mungtopia.dto.user.Info;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.*;
import com.d209.mungtopia.repository.user.UserRepository;
import com.d209.mungtopia.utils.FileUtil;
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

import javax.servlet.ServletContext;
import java.io.*;
import java.net.MalformedURLException;
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
    private final static String path = "https://i7d209.p.ssafy.io:8081/api/v1/image/";
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
    private final InfChatRoomRepository chatRoomRepository;
    private final InfAdoptionProcessRepository adoptionProcessRepository;
    private final InfAdoptionStepDateRepository adoptionStepDateRepository;
    private final FileUtil fileUtil;
    private final ServletContext servletContext;


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
                dogInfo.getNeutering(),
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
    public List<BoardListDto.Response> search(Long tagNo, int pageNo, long userSeq, String keyword) {
        System.out.println("keyword = " + keyword);
        List<Board> boardList = null;
        List<BoardListDto.Response> response = new ArrayList<>();
        if (tagNo == 0){
            boardList = boardRepository.findAll(keyword);
        }else if (tagNo == 1){
            boardList = boardRepository.findAllByBoardTag(boardTag[tagNo.intValue() - 1], keyword);
        }else{
            boardList = boardRepository.findAllByBoardTag(boardTag[tagNo.intValue() - 1], keyword);
        }

        for (Board board: boardList) {
            BoardListDto.Response result = sameInfoLogic(board, userSeq);
            if (board.getBoardTag().equals("입양")){
                DogInfo dogInfo = board.getDogInfo();
                result.setDogName(dogInfo.getName());
                result.setDogInfo(setDogInfoDto(dogInfo));
            }
            response.add(result);
        }
        return response;
    }

    /**
     * 글 저장
     * @param multipartFiles
     * @param boardDto
     * @return
     */
    @Override
    public Board saveBoard(List<MultipartFile> multipartFiles, BoardDto boardDto) throws Exception {
        Optional<User> user = userRepository.findById(boardDto.getUserSeq());
        if (user.isEmpty())
            return null;

        // 글 저장
        Board board = Board.builder()
                .boardTag(boardTag[boardDto.getBoardTag() - 1])
                .contents(boardDto.getContents())
                .createtime(boardDto.getCreatetime() == null ? getNow() : boardDto.getCreatetime())
                .user(user.get())
                .build();
        boardRepository.save(board);

        // 이미지 저장
        try{
            System.out.println("board.getBoardId() = " + board.getBoardId());
            List<ImageStorage> imageStorages = saveImgFile(multipartFiles, board);
            board.setImageStorageList(imageStorages);
        }catch (Exception e){
            throw  new Exception(e);
        }


        // 입양일 경우 추가 저장
        if (boardDto.getBoardTag() == 1) {
            DogInfoDto inputDogInfo = boardDto.getDogInfo();
            DogInfo dogInfo = DogInfo.builder()
                    .board(board)
                    .name(inputDogInfo.getName())
                    .areaSido(inputDogInfo.getAreaSido())
                    .areaGugun(inputDogInfo.getAreaGugun())
                    .gender(inputDogInfo.getGender())
                    .age(inputDogInfo.getAge())
                    .weight(inputDogInfo.getWeight())
                    .breed(inputDogInfo.getBreed())
                    .vaccination(inputDogInfo.isVaccination())
                    .neutering(inputDogInfo.isNeutering())
                    .adoptionStatus(inputDogInfo.isAdoptionStatus())
                    .build();
            dogInfoRepository.save(dogInfo);
            board.setDogInfo(dogInfo);

            DogNature dogNature = DogNature.builder()
                    .dogInfo(dogInfo)
                    .nature1(boardDto.getDogInfo().getDogNature().get(0))
                    .nature2(boardDto.getDogInfo().getDogNature().get(1))
                    .nature3(boardDto.getDogInfo().getDogNature().get(2))
                    .nature4(boardDto.getDogInfo().getDogNature().get(3))
                    .nature5(boardDto.getDogInfo().getDogNature().get(4))
                    .nature6(boardDto.getDogInfo().getDogNature().get(5))
                    .build();

            dogNatureRepository.save(dogNature);
            dogInfo.setDogNature(dogNature);
        }
        return board;
    }

    @Override
    public Board updateBoard(List<MultipartFile> multipartFiles, BoardDto boardDto, Board board) throws Exception {
        //Contents 수정
        board.setContents(boardDto.getContents());

        //image 수정
        List<ImageStorage> imageStorageList = board.getImageStorageList();
        if (!imageStorageList.isEmpty()) {
            //기존 이미지 모두 지우기
            for (ImageStorage imageStorage : imageStorageList) {
                imageStorageRepository.delete(imageStorage);
                File file = new File("/var/images/" + imageStorage.getSaveFileName());

                if (file.exists()){
                    if (file.delete())
                        System.out.println("=============== 파일 삭제 성공 =================");
                    else
                        System.out.println("=============== 파일 삭제 실패 =================");
                }else{
                    System.out.println("=============== 파일이 존재하지 않음 =================");
                }
                // 서버 이미지도 지워야함!!
            }
        }

        //이미지 새로 저장하기
        // 이미지 저장
        try{
            List<ImageStorage> imageStorages = saveImgFile(multipartFiles, board);
            board.setImageStorageList(imageStorages);
        }catch (Exception e){
            throw  new Exception(e);
        }


        //DogInfo 수정
        DogInfoDto inputDogInfo = boardDto.getDogInfo();
        DogInfo dogInfo = board.getDogInfo();
        dogInfo.setName(inputDogInfo.getName());
        dogInfo.setAreaSido(inputDogInfo.getAreaSido());
        dogInfo.setAreaGugun(inputDogInfo.getAreaGugun());
        dogInfo.setGender(inputDogInfo.getGender());
        dogInfo.setAge(inputDogInfo.getAge());
        dogInfo.setWeight(inputDogInfo.getWeight());
        dogInfo.setBreed(inputDogInfo.getBreed());
        dogInfo.setVaccination(inputDogInfo.isVaccination());
        dogInfo.setNeutering(inputDogInfo.isNeutering());
        dogInfo.setAdoptionStatus(inputDogInfo.isAdoptionStatus());
        dogInfoRepository.save(dogInfo);

        //DogNature 수정
        DogNature dogNature = dogInfo.getDogNature();
        dogNature.setNature1(boardDto.getDogInfo().getDogNature().get(0));
        dogNature.setNature2(boardDto.getDogInfo().getDogNature().get(1));
        dogNature.setNature3(boardDto.getDogInfo().getDogNature().get(2));
        dogNature.setNature4(boardDto.getDogInfo().getDogNature().get(3));
        dogNature.setNature5(boardDto.getDogInfo().getDogNature().get(4));
        dogNature.setNature6(boardDto.getDogInfo().getDogNature().get(5));
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
        //유저
        User user = userRepository.findById(appDto.getUserSeq()).get();

        //application
        Application application = Application.builder()
                .boardId(board.getBoardId())
                .send(true)
                .name(user.getUsername())
                .userInfo(user.getUserInfo())
                .user(user)
                .createtime(LocalDateTime.now())
                .applicationStatus(0)
                .build();

        //입양 신청서 질문
        List<Answer> answerList = new ArrayList<>();
        for (AnswerDto answerDto : appDto.getApplicantAnswerList()) {
            Answer answer = new Answer(answerDto, application);
            answerRepository.save(answer);
            answerList.add(answer);
        }
        application.setAnswerList(answerList);
        applicationRepository.save(application);

        //==================입양 절차 관련 테이블 생성=============================
        //chatroom
        ChatRoom chatRoom = ChatRoom.builder()
                .protectorId(board.getUser().getUserSeq())
                .protectorNickname(board.getUser().getNickname())
                .applicantId(user.getUserSeq())
                .applicantNickname(user.getNickname())
                .createtime(getNow())
//                .adoptionProcess()
                .build();

        //adoption_step_date
        List<AdoptionStepDate> adoptionStepDateList = new ArrayList<>();

        //adoption process
        AdoptionProcess adoptionProcess = AdoptionProcess.builder()
                .step(1)
                .stepStatus(Boolean.FALSE)
                .application(application)
                .chatRoom(chatRoom)
                .adoptionStepDateList(adoptionStepDateList)
                .build();
        adoptionProcessRepository.save(adoptionProcess);

        AdoptionStepDate adoptionStepDate = new AdoptionStepDate();
        adoptionStepDate.setAdoptionProcess(adoptionProcess);
        adoptionStepDateRepository.save(adoptionStepDate);
        adoptionStepDateList.add(adoptionStepDate);

        chatRoom.setAdoptionProcess(adoptionProcess);
        chatRoomRepository.save(chatRoom);

    //==========================================================================
        return application;
    }

    @Override
    public Boolean getLikes(User user, Board board) {
        System.out.println("user.getUserSeq() = " + user.getUserSeq());
        System.out.println("board.getBoardId() = " + board.getBoardId());
        return likeRepository.findLikesByUserAndBoard(user, board).isPresent();
    }

    @Override
    public Boolean likes(User user, Board board) {
        Likes likes = Likes.builder() //롬복의 @Builder 어노테이션 사용
                .createtime(getNow())
                .user(user)
                .board(board)
                .build();
        likeRepository.save(likes);

        return likeRepository.findLikesByUserAndBoard(user, board).isPresent();
    }

    @Override
    public Boolean unlikes(User user, Board board) {
        Optional<Likes> likes = likeRepository.findLikesByUserAndBoard(user, board);
        likeRepository.delete(likes.get());
        return likeRepository.findLikesByUserAndBoard(user, board).isPresent();
    }

    @Override
    public Boolean getStar(User user, Board board) {
        System.out.println("user.getUserSeq() = " + user.getUserSeq());
        System.out.println("board.getBoardId() = " + board.getBoardId());
        return starRepository.findStarByUserAndBoard(user, board).isPresent();
    }

    @Override
    public Boolean star(User user, Board board) {
        Star star = Star.builder() //롬복의 @Builder 어노테이션 사용
                .createtime(getNow())
                .user(user)
                .board(board)
                .build();
        starRepository.save(star);
        return starRepository.findStarByUserAndBoard(user, board).isPresent();
    }

    @Override
    public Boolean unstar(User user, Board board) {
        Optional<Star> star = starRepository.findStarByUserAndBoard(user, board);
        starRepository.delete(star.get());
        return starRepository.findStarByUserAndBoard(user, board).isPresent();
    }

    public List<CommentRes> getCommentAll(Board board){
        System.out.println("board.getBoardId() = " + board.getBoardId());
        //전체 댓글 리스트 반환
        List<Comment> commentList = commentRepository.findByBoard(board);
        if (commentList.isEmpty()) {
            return null;
        }
        List<CommentRes> commentResList = new ArrayList<CommentRes>();
        for(Comment cmt : commentList) {
            User cmtUser = userRepository.findById(cmt.getUserSeq()).get();
            Info cmtInfo = new Info(cmtUser.getUserSeq(), cmtUser.getProfileImageUrl(), cmtUser.getUsername(), cmtUser.getNickname());

            //대댓글
            Optional<List<Reply>> replyList = Optional.ofNullable(cmt.getReplyList());
            List<ReplyRes> replyResList = new ArrayList<ReplyRes>();
            if (replyList.isPresent()) {
                for (Reply reply : replyList.get()) {
                    User repUser = userRepository.findById(reply.getUserSeq()).get();
                    Info repInfo = new Info(repUser.getUserSeq(), repUser.getProfileImageUrl(), repUser.getUsername(), repUser.getNickname());
                    ReplyRes replyRes = new ReplyRes(
                            reply.getReplyId(),
                            repInfo,
                            reply.getContent(),
                            reply.isSecret(),
                            reply.getCreatetime().toLocalDateTime()
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    replyResList.add(replyRes);
                }
            }
            CommentRes commentRes = new CommentRes(
                    cmt.getCommentId(),
                    cmtInfo,
                    cmt.getContents(),
                    cmt.isSecret(),
                    cmt.getCreatetime().toLocalDateTime()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    replyResList);
            commentResList.add(commentRes);
        }
        return commentResList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentRes> CommentAll(Board board) {
        return getCommentAll(board);
    }

    public List<CommentRes> saveComment(Board board, CommentDto commentDto) {
        Comment comment = Comment.builder()
                .userSeq(commentDto.getUserSeq())
                .userNickname(commentDto.getUserNickname())
                .contents(commentDto.getContents())
                .secret(commentDto.isSecret())
                .createtime(getNow())
                .board(board)
                .build();
        commentRepository.save(comment);
        commentRepository.flush();

        return getCommentAll(board);
    }

    @Override
    public List<CommentRes> updateComment(Board board, Comment comment, CommentDto commentDto) {
        comment.setContents(commentDto.getContents());
        comment.setSecret(commentDto.isSecret());
//        comment.setCreatetime(getNow());
        commentRepository.save(comment);
        return getCommentAll(board);
    }


    @Override
    public List<CommentRes> deleteComment(Board board, Comment comment) {
        commentRepository.delete(comment);
        return getCommentAll(board);
    }

    @Override
    public List<CommentRes> saveReply(Board board, Comment comment, ReplyDto replyDto) {
        Reply reply = Reply.builder()
                .userSeq(replyDto.getUserSeq())
                .userNickname(replyDto.getUserNickname())
                .content(replyDto.getContents())
                .secret(replyDto.isSecret())
                .createtime(getNow())
                .comment(comment)
                .build();
        replyRepository.save(reply);
        return getCommentAll(board);
    }

    @Override
    public List<CommentRes> updateReply(Board board, Reply reply, ReplyDto replyDto) {
        System.out.println("reply = " + reply.getReplyId());
        reply.setContent(replyDto.getContents());
        reply.setSecret(replyDto.isSecret());
//        reply.setCreatetime(getNow());
        replyRepository.save(reply);
        return getCommentAll(board);
    }

    @Override
    public List<CommentRes> deleteReply(Board board, Reply reply) {
        replyRepository.delete(reply);
        return getCommentAll(board);
    }

    @Override
    @Transactional
    public List<ImageStorage> saveImgFile(List<MultipartFile> multipartFiles, Board board) throws Exception {
        // 비어있으면 false
        if (multipartFiles.isEmpty())
            return Collections.emptyList();

        // 파일 정보를 담을 수 있는 리스트
        List<ImageStorage> imageStorageDtoList = new ArrayList<>();

        // 서버에서 / 로 출력
        String root = System.getProperty("user.dir").toString() + "var/images";

        int order = 1;

        File dir = new File(root);
        if (!dir.exists()){
            // 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리 생성
            dir.mkdir();
        }

        // 파일 개수 만큼 forEach
        for (MultipartFile file : multipartFiles) {
            if (file.isEmpty()) // 파일이 비어있으면 false;
                return Collections.emptyList();

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

            ImageStorage imageStorage = new ImageStorage(order,
                    file.getOriginalFilename(),
                    path + saveName,
                    saveName,
                    board);
            imageStorageDtoList.add(imageStorage);
            order++;
        }

        imageStorageRepository.saveAll(imageStorageDtoList);

        return imageStorageDtoList;
    }

    @Override
    public Resource getImgFile(String fileName) throws IOException {
        try{
            Resource urlResource = new FileUrlResource("/var/images/" + fileName);
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
