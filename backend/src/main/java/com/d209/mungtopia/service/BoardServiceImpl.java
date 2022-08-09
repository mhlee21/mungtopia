package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.CommentDto;
import com.d209.mungtopia.dto.LikesDto;
import com.d209.mungtopia.dto.ReplyDto;
import com.d209.mungtopia.dto.StarDto;
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
    private final InfStarRepository starRepository;
    private final InfCommentRepository commentRepository;
    private final InfReplyRepository replyRepository;
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
    }

    @Override
    public Boolean star(User user, Board board) {
        StarDto starDto = new StarDto(user, board);

        //이미 좋아요 한 board 인 경우 409 에러
        if (starRepository.findStarByUserAndBoard(user,board).isPresent()) {
            return false;
        }

        Star star = Star.builder() //롬복의 @Builder 어노테이션 사용
                .createtime(new Timestamp(System.currentTimeMillis()))
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
                .createtime(new Timestamp(System.currentTimeMillis()))
                .board(board)
                .build();
        commentRepository.save(comment);
        return commentRepository.findByBoard(board);
    }

    @Override
    public List<Comment> updateComment(Board board, Comment comment, CommentDto commentDto) {
        comment.setContents(commentDto.getContents());
        comment.setSecret(commentDto.isSecret());
//        comment.setCreatetime(new Timestamp(System.currentTimeMillis()));
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
                .createtime(new Timestamp(System.currentTimeMillis()))
                .comment(comment)
                .build();
        replyRepository.save(reply);
        return commentRepository.findByBoard(board);
    }

    @Override
    public List<Comment> updateReply(Board board, Reply reply, ReplyDto replyDto) {
        reply.setContent(replyDto.getContent());
        reply.setSecret(replyDto.isSecret());
//        reply.setCreatetime(new Timestamp(System.currentTimeMillis()));
        replyRepository.save(reply);
        return commentRepository.findByBoard(board);
    }

    @Override
    public List<Comment> deleteReply(Board board, Reply reply, ReplyDto replyDto) {
        replyRepository.delete(reply);
        return commentRepository.findByBoard(board);
    }
}
