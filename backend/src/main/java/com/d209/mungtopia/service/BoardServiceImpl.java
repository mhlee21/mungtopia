package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.LikesDto;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

//    private final BoardRepository boardRepository;
    private final InfBoardRepository boardRepository;
    private final InfLikeRepository likeRepository;
    private final InfCommentRepository commentRepository;
    private final DogInfoRepository dogInfoRepository;

    @Override
    public List<Board> findBoardAll(Long tagNo, int pageNo) {
        /*
         * 전체 : 0
         * 입양 : 1 (추천하는 강아지 글 3개 먼저 보여주고 나머지는 최신순으로 보여줌
         * 후기 : 2
         * 자유 : 3
         */
//        System.out.printf("%d %d\n", tagNo, pageNo);
        List<Board> boardList = boardRepository.findAll();
        List<DogInfo> dogInfoList = dogInfoRepository.findDogInfoAll();


        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Board> findBoardDetail(Long boardId) {
        return boardRepository.findById(boardId);
    }

    @Override
    public Boolean likes(User user, Board board) {
        LikesDto likesDto = new LikesDto(user, board);

        //이미 좋아요 한 board 인 경우 409 에러
        if (likeRepository.findLikesByUserAndBoard(user,board).isPresent()) {
            return false;
        }

        Likes likes = Likes.builder() //롬복의 @Builder 어노테이션 사용
                .createtime(new Timestamp(System.currentTimeMillis()))
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
    };

    @Override
    public Boolean postBoardStar(Long boardId) {
        return null;
    }

    @Override
    public Boolean deleteBoardStar(Long boardId) {
        return null;
    }

    @Override
    public List<Comment> CommentAll() {
        return commentRepository.findAll();
    }

    public List<Comment> saveComment(Comment cmt) {
        commentRepository.save(cmt);
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> deleteComment(Comment cmt) {
        commentRepository.delete(cmt);
        return commentRepository.findAll();
    }


}
