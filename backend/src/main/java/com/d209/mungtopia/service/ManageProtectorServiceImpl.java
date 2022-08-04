package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.protector.detailApplication;
import com.d209.mungtopia.dto.protector.protectorBoardList;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.ManageProtectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// BL
@Service
@RequiredArgsConstructor
public class ManageProtectorServiceImpl implements ManageProtectorService{

    private final ManageProtectorRepository manageProtectorRepository;

    @Override
    public List<protectorBoardList> mainBoardInfo(Long userId) {
        List<protectorBoardList> protectorBoardLists = new ArrayList<>();

        List<Board> boardList = manageProtectorRepository.findBoardList(userId);

        for (int i = 0; i < boardList.size(); i++) {
            protectorBoardList protectorBoard = new protectorBoardList();

            Board board = boardList.get(i);

            protectorBoard.setBoardId(board.getBoardId());
            protectorBoard.setAdoptionStatus(board.getDogInfo().getAdoptionStatus());
            protectorBoard.setDogName(board.getDogInfo().getName());

            for (int j = 0; j < board.getImageStorageList().size(); j++) {
                Integer orders = board.getImageStorageList().get(j).getOrders();
                if (orders.equals(1)){
                    protectorBoard.setDogImgUrl(board.getImageStorageList().get(j).getFilename());
                }
            }

            protectorBoardLists.add(protectorBoard);
        }

        return protectorBoardLists;
    }

    @Override
    public protectorBoardList detailApplicantInfo(Long boardId) {
        protectorBoardList applicantList = new protectorBoardList();

        Board board = manageProtectorRepository.findByBoardId(boardId);
        // board_id 값 세팅
        applicantList.setBoardId(boardId);
        // dog_name 값 세팅
        applicantList.setDogName(board.getDogInfo().getName());
        // dog_url 값 세팅
        for (int i = 0; i < board.getImageStorageList().size(); i++) {
            ImageStorage imageStorage = board.getImageStorageList().get(i);
            if (imageStorage.getOrders().equals(1)){
                applicantList.setDogImgUrl(imageStorage.getFilename());
            }
        }

        // GET board_id에 대해 신청서 작성한 사람
        List<Application> applicants = manageProtectorRepository.findApplicantList(boardId);
        System.out.println("applicants.size() = " + applicants.size());
        // applicant를 applicationList의 applicationList에 넣어준다.
        for (Application curApplication : applicants) {
            // 현재 applicant
            // list에 넣을 객체
            detailApplication detailApplication = new detailApplication();
            // userId
            detailApplication.setUserId(curApplication.getUser().getUserSeq());
            // userImg
            detailApplication.setProfileImg(curApplication.getUser().getProfileImageUrl());
            // userName
            detailApplication.setUsername(curApplication.getName());
            // applicationStatus
            detailApplication.setApplicationStatus(curApplication.getApplicationStatus());
            System.out.println("curApplication.getApplicationStatus() = " + curApplication.getApplicationStatus());
            AdoptionProcess adoptionProcess = curApplication.getAdoptionProcess();
            detailApplication.setAdoptionProcessId(adoptionProcess.getAdoptionProcessId());

            detailApplication.setMeetingRoomId(adoptionProcess.getMeetingRoom().getMeetingRoomId());
            detailApplication.setActive(adoptionProcess.getMeetingRoom().getActive());
            detailApplication.setChatRoomId(adoptionProcess.getChatRoom().getChatRoomId());

            applicantList.getApplicationList().add(detailApplication);
        }

        return applicantList;
    }
}
