package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.manage.AnswerDto;
import com.d209.mungtopia.dto.manage.ApplicationDto;
import com.d209.mungtopia.dto.manage.ChatDto;
import com.d209.mungtopia.dto.protector.ApplicantProcessRes;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.AdoptionProcessRepository;
import com.d209.mungtopia.repository.AdoptionStepDateRepository;
import com.d209.mungtopia.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManageServiceImpl implements ManageService{

    private final ApplicationRepository applicationRepository;
    private final AdoptionProcessRepository adoptionProcessRepository;
    private final AdoptionStepDateRepository adoptionStepDateRepository;
    private final IAdoptionStepDateRepository iAdoptionStepDateRepository;

    @Override
    public ApplicationDto getApplication(Long appId) {
        Application application = applicationRepository.findOne(appId);
        ApplicationDto result = new ApplicationDto(
                application.getUserInfo(),
                application.getCreatetime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                application.getApplicationStatus()
        );
        result.getUserInfo().changeName(application.getName());
        List<AnswerDto> answers = new ArrayList<>();
        List<Answer> answerList = application.getAnswerList();
        // 정렬..?
        answerList.sort(new Comparator<Answer>() {
            @Override
            public int compare(Answer o1, Answer o2) {
                return Integer.compare(o1.getOrder(), o2.getOrder());
            }
        });
        for (Answer answer: answerList) {
            AnswerDto answerDto = new AnswerDto(answer.getOrder(), answer.getAnswer());
            answers.add(answerDto);
        }
        result.setAnswer(answers);
        return result;
    }

    @Override
    public ChatDto getChatting() {
        return null;
    }

    @Override
    public Object createChat() {
        return null;
    }

    @Override
    public ApplicantProcessRes getSchedule(Long adoptionProcessId) {
        ApplicantProcessRes response = new ApplicantProcessRes();
        AdoptionProcess adoptionProcess = adoptionProcessRepository.find(adoptionProcessId);
        int step = adoptionProcess.getStep(); // 현재 step
        List<AdoptionStepDate> adoptionStepDateList = adoptionProcess.getAdoptionStepDateList();
        for (AdoptionStepDate stepDate: adoptionStepDateList) {
            if(stepDate.getStep() == step){
                response.setStep(step);
                response.setDate(stepDate.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        }
        return response;
    }

    @Override
    @Transactional
    public Boolean postSchedule(Long adoptionProcessId, LocalDateTime dateTime) {
        AdoptionProcess adoptionProcess = adoptionProcessRepository.find(adoptionProcessId);
        int step = adoptionProcess.getStep(); // 현재 step
        List<AdoptionStepDate> adoptionStepDateList = adoptionProcess.getAdoptionStepDateList();
        for (AdoptionStepDate stepDate: adoptionStepDateList) {
            if(stepDate.getStep() == step){
                stepDate.changeDate(dateTime); // 날짜 수정
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteSchedule(Long adoptionProcessId) {
        AdoptionProcess adoptionProcess = adoptionProcessRepository.find(adoptionProcessId);
        int step = adoptionProcess.getStep(); // 현재 step
        List<AdoptionStepDate> adoptionStepDateList = adoptionProcess.getAdoptionStepDateList();
        for (AdoptionStepDate stepDate: adoptionStepDateList) {
            if(stepDate.getStep() == step){
                stepDate.changeDate(LocalDateTime.of(0, 0, 0, 0, 0)); // null로 수정  0000-00-00 00:00:00
                return true;
            }
        }
        return false;
    }
}
