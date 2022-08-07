package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.protector.ApplicantProcessDto;
import com.d209.mungtopia.dto.protector.DetailApplicationDto;
import com.d209.mungtopia.dto.protector.ProtectorBoardListDto;
import com.d209.mungtopia.dto.protector.StepUpdateDto;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.AdoptionProcessRepository;
import com.d209.mungtopia.repository.ManageProtectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// BL
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManageProtectorServiceImpl implements ManageProtectorService{

    private final ManageProtectorRepository manageProtectorRepository;
    private final AdoptionProcessRepository adoptionProcessRepository;

    /**
     * 입양 보내기 메인
     * @param userId
     * @return
     */
    @Override
    public List<ProtectorBoardListDto> mainBoardInfo(Long userId) {
        List<ProtectorBoardListDto> protectorBoardLists = new ArrayList<>();

        List<Board> boardList = manageProtectorRepository.findBoardList(userId);

        for (int i = 0; i < boardList.size(); i++) {
            ProtectorBoardListDto protectorBoard = new ProtectorBoardListDto();

            Board board = boardList.get(i);

            protectorBoard.setBoardId(board.getBoardId());
            protectorBoard.setAdoptionStatus(board.getDogInfo().getAdoptionStatus());
            protectorBoard.setDogName(board.getDogInfo().getName());

            for (int j = 0; j < board.getImageStorageList().size(); j++) {
                Integer orders = board.getImageStorageList().get(j).getOrders();
                if (orders.equals(1)){
                    protectorBoard.setDogImg(board.getImageStorageList().get(j).getFilename());
                }
            }

            protectorBoardLists.add(protectorBoard);
        }

        return protectorBoardLists;
    }

    /**
     * 입양 보내기 상세
     * @param boardId
     * @return
     */
    @Override
    public ProtectorBoardListDto detailApplicantInfo(Long boardId) {
        ProtectorBoardListDto applicantList = new ProtectorBoardListDto();

        Board board = manageProtectorRepository.findByBoardId(boardId);
        // board_id 값 세팅
        applicantList.setBoardId(boardId);
        // dog_name 값 세팅
        applicantList.setDogName(board.getDogInfo().getName());
        // dog_url 값 세팅
        for (int i = 0; i < board.getImageStorageList().size(); i++) {
            ImageStorage imageStorage = board.getImageStorageList().get(i);
            if (imageStorage.getOrders().equals(1)){
                applicantList.setDogImg(imageStorage.getFilename());
            }
        }

        // GET board_id에 대해 신청서 작성한 사람
        List<Application> applicants = manageProtectorRepository.findApplicantList(boardId);
        System.out.println("applicants.size() = " + applicants.size());
        // applicant를 applicationList의 applicationList에 넣어준다.
        for (Application curApplication : applicants) {
            // 현재 applicant
            // list에 넣을 객체
            DetailApplicationDto detailApplication = new DetailApplicationDto();
            // userId
            detailApplication.setUserId(curApplication.getUser().getUserSeq());
            // userImg
            detailApplication.setProfileImg(curApplication.getUser().getProfileImageUrl());
            // userName
            detailApplication.setUsername(curApplication.getName());
            // applicationStatus
            detailApplication.setApplicationStatus(curApplication.getApplicationStatus());
            AdoptionProcess adoptionProcess = curApplication.getAdoptionProcess();
            detailApplication.setAdoptionProcessId(adoptionProcess.getAdoptionProcessId());

            detailApplication.setMeetingRoomId(adoptionProcess.getMeetingRoom().getMeetingRoomId());
            detailApplication.setActive(adoptionProcess.getMeetingRoom().getActive());
            detailApplication.setChatRoomId(adoptionProcess.getChatRoom().getChatRoomId());

            applicantList.getApplicationList().add(detailApplication);
        }

        return applicantList;
    }

    /**
     * 입양 신청자 상세 정보
     * @param adoptionProcessId
     * @return
     */
    @Override
    public List<ApplicantProcessDto> applicantDetailProcess(Long adoptionProcessId) {
        AdoptionProcess adoptionProcess = manageProtectorRepository.findByAdoptionProcessId(adoptionProcessId);

        List<ApplicantProcessDto> applicantProcessList = new ArrayList<>();
        int curStep = adoptionProcess.getStep();
        List<AdoptionStepDate> adoptionStepDateList = adoptionProcess.getAdoptionStepDateList();
        adoptionStepDateList.sort(new Comparator<AdoptionStepDate>() {
            @Override
            public int compare(AdoptionStepDate o1, AdoptionStepDate o2) {
                return Integer.compare(o1.getStep(), o2.getStep());
            }
        });

        for (AdoptionStepDate adoptionStepDate : adoptionStepDateList) {
            ApplicantProcessDto process = new ApplicantProcessDto();
            process.setStep(adoptionStepDate.getStep());

//            Date date = new Date(adoptionStepDate.getDate() * 1000);

            process.setDate(adoptionStepDate.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            if (adoptionStepDate.getStep() < curStep) {
                process.setStepStatus(true);
            } else  {
                process.setStepStatus(adoptionProcess.getStepStatus());
            }

            applicantProcessList.add(process);
        }

        return applicantProcessList;
    }

    /**
     * 입양 반려
     * @param processId
     * @return
     */
    @Transactional
    public Boolean deleteProcess(Long processId){
        AdoptionProcess adoptionProcess = adoptionProcessRepository.find(processId);
        Application application = adoptionProcess.getApplication();
        application.setApplicationStatus(8); // protector가 취소한 경우 8로 저장
        return application.getApplicationStatus().equals(8);
    }

    /**
     * 입양 상태 변경
     * @param adoptionProcessId
     * @param stepUpdateInfo
     * @return
     */
    @Transactional
    public List<ApplicantProcessDto> updateProcessStatus(Long adoptionProcessId, StepUpdateDto stepUpdateInfo){
        AdoptionProcess adoptionProcess = adoptionProcessRepository.find(adoptionProcessId);
        adoptionProcess.setStep(stepUpdateInfo.getStep());
        if (stepUpdateInfo.getStep() == 5){ // true
            List<AdoptionStepDate> adoptionStepDateList = adoptionProcess.getAdoptionStepDateList();
            for (AdoptionStepDate adoptionStepDate : adoptionStepDateList) {
                if(adoptionStepDate.getStep().equals(5)){
                    adoptionStepDate.setDate(LocalDateTime.now());
                }
            }
        }
        adoptionProcess.setStepStatus(stepUpdateInfo.getStepStatus());
        return applicantDetailProcess(adoptionProcessId);
    }
}
