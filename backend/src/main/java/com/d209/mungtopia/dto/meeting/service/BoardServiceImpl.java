package com.d209.mungtopia.dto.meeting.service;

import com.d209.mungtopia.dto.*;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.*;
import com.d209.mungtopia.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {
    static String[] boardTag = {"입양", "후기", "잡담"};
    private final UserRepository userRepository;
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

    public Timestamp getNow(){
        return new Timestamp(System.currentTimeMillis());
    }

    @Override
    public List<Board> findBoardAll(Long tagNo, int pageNo) {
        /*
         * 전체 : 0
         * 입양 : 1 (추천하는 강아지 글 3개 먼저 보여주고 나머지는 최신순으로 보여줌
         * 후기 : 2
         * 자유 : 3
         */
        List<Board> boardList = boardRepository.findAllByBoardTag(boardTag[tagNo.intValue()-1]);
        return boardList;
    }

    @Override
    public List<Board> search(Long tagNo, int pageNo) {
        List<Board> boardList = boardRepository.findAllByBoardTag(boardTag[tagNo.intValue()-1]);
        return boardList;
    }

    @Override
    public Board saveBoard(Long tagNo, BoardDto boardDto) {
        User user = userRepository.findById(boardDto.getUserSeq()).get();

        Board board = Board.builder()
                .boardTag(boardTag[tagNo.intValue()-1])
                .contents(boardDto.getContents())
                .createtime(getNow())
                .user(user)
                .build();
        boardRepository.save(board);

        List<ImageStorage> imageStorageList = new ArrayList<>();
        for (ImageStorageDto imageStorageDto: boardDto.getImageStorageDtoList()) {
            ImageStorage imageStorage = ImageStorage.builder()
                    .orders(imageStorageDto.getOrders())
                    .filename(imageStorageDto.getFilename())
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
        if (! imageStorageList.isEmpty()) {
            //기존 이미지 모두 지우기
            for (ImageStorage imageStorage : imageStorageList) {
                imageStorageRepository.delete(imageStorage);
            }
        }

        //이미지 새로 저장하기
        List<ImageStorage> newImageStorageList = new ArrayList<>();
        for (ImageStorageDto imageStorageDto: boardDto.getImageStorageDtoList()) {
            ImageStorage imageStorage = ImageStorage.builder()
                    .orders(imageStorageDto.getOrders())
                    .filename(imageStorageDto.getFilename())
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
        for (AnswerDto answerDto: appDto.getApplicantAnswerList()) {
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
        if (likeRepository.findLikesByUserAndBoard(user,board).isPresent()) {
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
        if (starRepository.findStarByUserAndBoard(user,board).isPresent()) {
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
}