package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.game.GameReq;
import com.d209.mungtopia.dto.game.MatchingGameReq;
import com.d209.mungtopia.dto.game.MatchingGameRes;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService{
    private final InfUserRepository infUserRepository;
    private final InfGameResultRepository infGameResultRepository;
    private final InfUserDogNatureRepository infUserDogNatureRepository;
    private final InfDogNatureRepository infDogNatureRepository;
    private final InfBoardRepository infBoardRepository;
    private final InfImageStorageRepository infImageStorageRepository;

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

        Optional<GameResult> gameResult = infGameResultRepository.findByUserAndGameTag(user, gameReq.getGameTag());
        if (gameResult.isEmpty()){ // 기존에 게임내역이 없을 경우에만 저장
            GameResult result = new GameResult(gameReq.getGameTag(), user);
            infGameResultRepository.save(result);
        }
        return true;
    }

    @Override
    public MatchingGameRes postMatchingResult(MatchingGameReq gameReq) {
        List<Integer> userNature = gameReq.getMatchAnswer(); // value 결과 저장
        Optional<User> user = Optional.ofNullable(infUserRepository.getReferenceById(gameReq.getUserSeq()));
        if (user.isEmpty()) // null 처리
            return null;
        // 결과 저장로직
        Optional<UserDogNature> userDogNature = infUserDogNatureRepository.findByUser(user.get());
        if (userDogNature.isEmpty()){ // 없으면 새로 저장
            UserDogNature saveUserDogNature = new UserDogNature();
            saveUserDogNature.saveUser(user.get(), userNature);
            infUserDogNatureRepository.save(saveUserDogNature);
        }else { // 있으면 기존 것 수정
            userDogNature.get().saveUser(user.get(), userNature);
            infUserDogNatureRepository.save(userDogNature.get());
        }

        // 결과 리턴하기
        List<DogNature>  dogNatureList = infDogNatureRepository.findAll();
        Map<Long, Integer> result = new HashMap<>();
        for (int i = 0; i < dogNatureList.size(); i++) {
            DogNature dogNature = dogNatureList.get(i);
            long boardId = dogNature.getDogInfo().getBoard().getBoardId();
            int sum = 0;
            sum += Math.abs(userNature.get(0) - dogNature.getNature1() * 3);
            sum += Math.abs(userNature.get(1) - dogNature.getNature2() * 3);
            sum += Math.abs(userNature.get(2) - dogNature.getNature3() * 3);
            sum += Math.abs(userNature.get(3) - dogNature.getNature4() * 3);
            sum += Math.abs(userNature.get(4) - dogNature.getNature5() * 3);
            sum += Math.abs(userNature.get(5) - dogNature.getNature6() * 3);

            result.put(boardId, sum); // 결과 저장
        }
        // 정렬
        List<Map.Entry<Long, Integer>> resultList = new LinkedList<>(result.entrySet());
        resultList.sort(Map.Entry.comparingByValue());
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println("resultList Key = " + resultList.get(i).getKey());
            System.out.println("resultList Value = " + resultList.get(i).getValue());
        }
        System.out.println("resultList = " + resultList);
        int randomDog = (int) (Math.random() * 3); // 1 - 3 중 랜덤으로 리턴
        Long resultKey = resultList.get(randomDog).getKey();
        Integer resultSum = resultList.get(randomDog).getValue();
        int percent = (int) (100 - (( 1.0 * resultSum / (90 - 18)) * 100));


        MatchingGameRes response = new MatchingGameRes();
        Optional<Board> targetBoard = infBoardRepository.findById(resultKey);
        if (targetBoard.isEmpty())
            return null;

        response.setBoardId(resultKey);
        response.setDogImg(infImageStorageRepository.findByBoardAndOrders(targetBoard.get(), 1).getServerPath());
        response.setDogName(targetBoard.get().getDogInfo().getName());
        response.setPercent(percent);
        return response;
    }
}
