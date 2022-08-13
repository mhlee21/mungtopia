package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.protector.ApplicantProcessRes;
import com.d209.mungtopia.dto.protector.DetailApplicationDto;
import com.d209.mungtopia.dto.protector.ProtectorBoardListRes;
import com.d209.mungtopia.dto.protector.StepRes;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.AdoptionProcessRepository;
import com.d209.mungtopia.repository.InfAdoptionStepDateRepository;
import com.d209.mungtopia.repository.ManageProtectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
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
    private final InfAdoptionStepDateRepository infAdoptionStepDateRepository;

    /**
     * 입양 보내기 메인
     * @param userId
     * @return
     */
    @Override
    public List<ProtectorBoardListRes> mainBoardInfo(Long userId) {

        List<ProtectorBoardListRes> protectorBoardLists = new ArrayList<>();

        List<Board> boardList = manageProtectorRepository.findBoardList(userId);

        for (int i = 0; i < boardList.size(); i++) {
            ProtectorBoardListRes protectorBoard = new ProtectorBoardListRes();

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
    public ProtectorBoardListRes detailApplicantInfo(Long boardId) {
        ProtectorBoardListRes applicantList = new ProtectorBoardListRes();

        Board board = manageProtectorRepository.findByBoardId(boardId);
        // board_id 값 세팅
        applicantList.setBoardId(boardId);
        // dog_name 값 세팅
        applicantList.setDogName(board.getDogInfo().getName());
        // dog_url 값 세팅
        for (int i = 0; i < board.getImageStorageList().size(); i++) {
            ImageStorage imageStorage = board.getImageStorageList().get(i);
            if (imageStorage.getOrders() == 1){
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
            detailApplication.setUserSeq(curApplication.getUser().getUserSeq());
            // applicationId
            detailApplication.setApplicationId(curApplication.getApplicationId());
            // userImg
            detailApplication.setProfileImg(curApplication.getUser().getProfileImageUrl());
            // userName
            detailApplication.setUsername(curApplication.getName());
            // applicationStatus
            detailApplication.setApplicationStatus(curApplication.getApplicationStatus());
            AdoptionProcess adoptionProcess = curApplication.getAdoptionProcess();
            detailApplication.setAdoptionProcessId(adoptionProcess.getAdoptionProcessId());

//            detailApplication.setMeetingRoomId(adoptionProcess.getMeetingRoom().getMeetingRoomId());
//            detailApplication.setActive(adoptionProcess.getMeetingRoom().getActive());
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
    public List<ApplicantProcessRes> applicantDetailProcess(Long adoptionProcessId) {
       return applicationProcessList(adoptionProcessId);
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
        application.changeApplicationStatus(8); // protector가 취소한 경우 8로 저장
        return application.getApplicationStatus().equals(8);
    }

    /**
     * 입양 상태 변경
     * @param adoptionProcessId
     * @param stepUpdateInfo
     * @return
     */
    @Transactional
    public List<ApplicantProcessRes> updateProcessStatus(Long adoptionProcessId, StepRes stepUpdateInfo){
        AdoptionProcess adoptionProcess = adoptionProcessRepository.find(adoptionProcessId);
        AdoptionStepDate newStep = new AdoptionStepDate(stepUpdateInfo.getStep() + 1, adoptionProcess);
        if (stepUpdateInfo.getStep() == 5){
            // 날짜 저장
            newStep.changeDate(LocalDateTime.now());
            // 5인 경우에 application status를 변경해줘야함!
            adoptionProcess.getApplication().changeApplicationStatus(6);
        }

        infAdoptionStepDateRepository.save(newStep);
        adoptionProcess.setStep(stepUpdateInfo.getStep() + 1); // 기존에서 step을 증가 시킴
        adoptionProcess.setStepStatus(false);

        return applicationProcessList(adoptionProcessId);
    }

    // 정보 리턴 - 공통 메서드
    private List<ApplicantProcessRes> applicationProcessList(Long adoptionProcessId){
        AdoptionProcess adoptionProcess = manageProtectorRepository.findByAdoptionProcessId(adoptionProcessId);
        List<AdoptionStepDate> adoptionStepDates = infAdoptionStepDateRepository.findByAdoptionProcessOrderByStep(adoptionProcess);

        List<ApplicantProcessRes> response = new ArrayList<>();
        int curStep = adoptionProcess.getStep();

        for (AdoptionStepDate adoptionStepDate : adoptionStepDates) {
            ApplicantProcessRes process = new ApplicantProcessRes();

            process.setStep(adoptionStepDate.getStep());
            Optional<LocalDateTime> datetime = Optional.ofNullable(adoptionStepDate.getDate());
            if (datetime.isEmpty())
                process.setDate(null);
            else
                process.setDate(datetime.get().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            if (adoptionStepDate.getStep() < curStep) {
                process.setStepStatus(true);
            } else  {
                process.setStepStatus(adoptionProcess.getStepStatus());
            }
            response.add(process);
        }

        return response;
    }
}
