package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.applicant.ApplicationListInfoRes;
import com.d209.mungtopia.dto.applicant.DetailProcessRes;
import com.d209.mungtopia.dto.applicant.ProcessDetailRes;
import com.d209.mungtopia.dto.protector.ApplicantProcessRes;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManageApplicantServiceImpl implements ManageApplicantService {

    private final CUserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ApplicationRepository applicationRepository;
    private final AdoptionProcessRepository adoptionProcessRepository;
//    private final AdoptionStepDateRepository adoptionStepDateRepository;
    private final ImageStorageRepository imageStorageRepository;

    /**
     * 입양하기 메인
     * @param userId
     * @return
     */
    @Override
    public List<ApplicationListInfoRes> mainApplicationInfo(Long userId) {
        Optional<User> user = Optional.ofNullable(userRepository.findOne(userId));

        if (user.isEmpty()){ // 예외처리
            return null;
        }

        List<Application> userApplicationList = user.get().getApplicationList();

        if (userApplicationList.isEmpty()){ // 이때까지 한 신청이 없다면 null을 리턴
            return null;
        }

        List<ApplicationListInfoRes> responseList = new ArrayList<>();

        for (Application application : userApplicationList) {
            ApplicationListInfoRes response = new ApplicationListInfoRes();
            response.setApplicationId(application.getApplicationId());
            response.setApplicationStatus(application.getApplicationStatus());

            Long boardId = application.getBoardId();
            Board board = boardRepository.findOne(boardId);

            response.setDogName(board.getDogInfo().getName());

            List<ImageStorage> img = imageStorageRepository.findMainOne(boardId);
            response.setDogImg(img.get(0).getOriginFilename());
            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public ProcessDetailRes detailProcressInfo(Long appId) {
        ProcessDetailRes reponse = new ProcessDetailRes();

        Application application = applicationRepository.findOne(appId);
        Long boardId = application.getBoardId();
        reponse.setBoardId(boardId);
        reponse.setApplicationStatus(application.getApplicationStatus());

        AdoptionProcess adoptionProcess = application.getAdoptionProcess();
        reponse.setChatRoomId(adoptionProcess.getChatRoom().getChatRoomId());
//        reponse.setMeetingRoomId(adoptionProcess.getMeetingRoom().getMeetingRoomId());
//        reponse.setMeetingActive(adoptionProcess.getMeetingRoom().getActive());

        Board board = boardRepository.findOne(boardId);
        reponse.setDogName(board.getDogInfo().getName());
        reponse.setDogImg(boardRepository.findMainImg(boardId));
        return reponse;
    }

    @Override
    public DetailProcessRes processDetailInfo(Long appId) {
        DetailProcessRes response = new DetailProcessRes();

        Application application = applicationRepository.findOne(appId);
        Long adoptionProcessId = application.getAdoptionProcess().getAdoptionProcessId();
        response.setAdoptionProcessId(adoptionProcessId);

        AdoptionProcess adoptionProcess = adoptionProcessRepository.find(adoptionProcessId);

        int curStep = adoptionProcess.getStep(); // 지금까지 있는 step

        List<AdoptionStepDate> adoptionStepDateList = adoptionProcessRepository.findAdoptionStepDateList(adoptionProcessId);

        for (AdoptionStepDate adoptionStepDate : adoptionStepDateList) {
            ApplicantProcessRes process = new ApplicantProcessRes();

            process.setStep(adoptionStepDate.getStep());
            process.setDate(adoptionStepDate.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            if (adoptionStepDate.getStep() < curStep) {
                process.setStepStatus(true);
            } else  {
                process.setStepStatus(adoptionProcess.getStepStatus());
            }

            response.getApplicantProcessRes().add(process);
        }

        return response;
    }

    @Override
    @Transactional
    public Boolean cancelAdoption(Long adoptionProcessId) {
        AdoptionProcess adoptionProcess = adoptionProcessRepository.find(adoptionProcessId);
        Application application = adoptionProcess.getApplication();
        application.changeApplicationStatus(7);
        return applicationRepository.findOne(application.getApplicationId()).getApplicationStatus().equals(7);
    }
}