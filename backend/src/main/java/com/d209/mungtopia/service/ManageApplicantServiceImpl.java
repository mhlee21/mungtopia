package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.applicant.ApplicationListInfoDto;
import com.d209.mungtopia.dto.applicant.DetailProcessDto;
import com.d209.mungtopia.dto.applicant.ProcessDetailDto;
import com.d209.mungtopia.dto.protector.ApplicantProcessDto;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManageApplicantServiceImpl implements ManageApplicantService {

    private final CUserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ApplicationRepository applicationRepository;
    private final AdoptionProcessRepository adoptionProcessRepository;

    /**
     * 입양하기 메인
     * @param userId
     * @return
     */
    @Override
    public List<ApplicationListInfoDto> mainApplicationInfo(Long userId) {
        User user = userRepository.findOne(userId);
        List<Application> userApplicationList = user.getApplicationList();

        if (userApplicationList.isEmpty()){ // 이때까지 한 신청이 없다면 null을 리턴
            return null;
        }

        List<ApplicationListInfoDto> applicationList = new ArrayList<>();

        for (Application application : userApplicationList) {
            ApplicationListInfoDto info = new ApplicationListInfoDto();
            info.setApplicationId(application.getApplicationId());
            info.setApplicationStatus(application.getApplicationStatus());

            Long boardId = application.getBoardId();
            Board board = boardRepository.findOne(boardId);

            DogInfo dogInfo = board.getDogInfo();
            info.setDogName(dogInfo.getName());

            List<ImageStorage> imageStorageList = board.getImageStorageList();
            for (ImageStorage imageStorage: imageStorageList) {
               if (imageStorage.getOrders() == 1){
                   info.setDogImg(imageStorage.getFilename());
                   break;
               }
            }
            applicationList.add(info);
        }

        return applicationList;
    }

    @Override
    public ProcessDetailDto detailProcressInfo(Long appId) {
        ProcessDetailDto result = new ProcessDetailDto();

        Application application = applicationRepository.findOne(appId);
        Long boardId = application.getBoardId();
        result.setBoardId(boardId);
        result.setApplicationStatus(application.getApplicationStatus());

        AdoptionProcess adoptionProcess = application.getAdoptionProcess();
        result.setChatRoomId(adoptionProcess.getChatRoom().getChatRoomId());
        result.setMeetingRoomId(adoptionProcess.getMeetingRoom().getMeetingRoomId());
        result.setMeetingActive(adoptionProcess.getMeetingRoom().getActive());

        Board board = boardRepository.findOne(boardId);
        result.setDogName(board.getDogInfo().getName());
        result.setDogImg(boardRepository.findMainImg(boardId));
        return result;
    }

    @Override
    public DetailProcessDto processDetailInfo(Long appId) {
        DetailProcessDto result = new DetailProcessDto();

        Application application = applicationRepository.findOne(appId);
        Long adoptionProcessId = application.getAdoptionProcess().getAdoptionProcessId();
        result.setAdoptionProcessId(adoptionProcessId);

        AdoptionProcess adoptionProcess = adoptionProcessRepository.find(adoptionProcessId);

        List<ApplicantProcessDto> applicantProcessList = new ArrayList<>();
        int curStep = adoptionProcess.getStep(); // 지금까지 있는 step

        List<AdoptionStepDate> adoptionStepDateList = adoptionProcess.getAdoptionStepDateList(); // date 저장
        // step순으로 정렬
        adoptionStepDateList.sort(new Comparator<AdoptionStepDate>() {
            @Override
            public int compare(AdoptionStepDate o1, AdoptionStepDate o2) {
                return Integer.compare(o1.getStep(), o2.getStep());
            }
        });

        for (AdoptionStepDate adoptionStepDate : adoptionStepDateList) {
            ApplicantProcessDto process = new ApplicantProcessDto();
            process.setStep(adoptionStepDate.getStep());
            process.setDate(adoptionStepDate.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            if (adoptionStepDate.getStep() < curStep) {
                process.setStepStatus(true);
            } else  {
                process.setStepStatus(adoptionProcess.getStepStatus());
            }
            applicantProcessList.add(process);
        }

        result.setApplicantProcessDto(applicantProcessList);

        return result;
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