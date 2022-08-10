package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.game.GameReq;
import com.d209.mungtopia.dto.game.MatchingGameReq;
import com.d209.mungtopia.entity.GameResult;
import com.d209.mungtopia.entity.User;
import com.d209.mungtopia.entity.UserDogNature;
import com.d209.mungtopia.repository.InfGameResultRepository;
import com.d209.mungtopia.repository.InfUserDogNatureRepository;
import com.d209.mungtopia.repository.InfUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService{
    private final InfUserRepository infUserRepository;
    private final InfGameResultRepository infGameResultRepository;
    private final InfUserDogNatureRepository infUserDogNatureRepository;
    @Override
    public List<GameReq> getGameResult(long userSeq) {
        User user = infUserRepository.getReferenceById(userSeq);
        if (user == null)
            return null;

        List<GameResult> gameResultList = infGameResultRepository.findByUserOrderByGameTagAsc(user);
        if (gameResultList.isEmpty()) // 없으면 null 보내기
            return null;

        List<GameReq> response = new ArrayList<>();
        for (GameResult gameResult : gameResultList) {
            GameReq res = new GameReq();
            res.setUserSeq(gameResult.getUser().getUserSeq());
            res.setGameTag(gameResult.getGameTag());
            res.setResult(gameResult.getResult());
            response.add(res);
        }
        return response;
    }

    @Override
    public boolean postGameResult(GameReq gameReq) {
        long userSeq = gameReq.getUserSeq();
        User user = infUserRepository.getReferenceById(userSeq);
        if (user == null)
            return false;
        GameResult gameResult = new GameResult(gameReq.getGameTag(), user);
        infGameResultRepository.save(gameResult);
        return true;
    }

    @Override
    public boolean postMatchingResult(MatchingGameReq gameReq) {
        List<Integer> nature = gameReq.getResult(); // value 결과 저장
        Optional<User> user = Optional.ofNullable(infUserRepository.getReferenceById(gameReq.getUserSeq()));
        if (user.isEmpty()) // null 처리
            return false;
        // 결과 저장로직
        Optional<UserDogNature> userDogNature = infUserDogNatureRepository.findByUser(user.get());
        if (userDogNature.isEmpty()){ // 없으면 새로 저장
            UserDogNature saveUserDogNature = new UserDogNature();
            saveUserDogNature.saveUser(user.get(), nature);
            infUserDogNatureRepository.save(saveUserDogNature);
            return true;
        }else { // 있으면 기존 것 수정
            userDogNature.get().saveUser(user.get(), nature);
            infUserDogNatureRepository.save(userDogNature.get());
            return true;
        }
    }
}
