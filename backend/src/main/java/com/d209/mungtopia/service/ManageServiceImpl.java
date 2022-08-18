package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.manage.AnswerDto;
import com.d209.mungtopia.dto.manage.ApplicationDto;
import com.d209.mungtopia.dto.manage.ChatDto;
import com.d209.mungtopia.dto.protector.ApplicantProcessRes;
import com.d209.mungtopia.entity.*;
import com.d209.mungtopia.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
    private final InfAdoptionStepDateRepository infAdoptionStepDateRepository;
    private final InfAdoptionProcessRepository infAdoptionProcessRepository;

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
                return Integer.compare(o1.getIdx(), o2.getIdx());
            }
        });
        for (Answer answer: answerList) {
            AnswerDto answerDto = new AnswerDto(answer.getIdx(), answer.getAnswer());
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
        Optional<AdoptionProcess> adoptionProcess = infAdoptionProcessRepository.findById(adoptionProcessId);
        if (adoptionProcess.isEmpty()) // 없으면 null 반환
            return null;
        // 있으면 찾아서 반환
        int step = adoptionProcess.get().getStep(); // 현재 step
        Optional<AdoptionStepDate> stepDate = infAdoptionStepDateRepository.findByAdoptionProcessAndStep(adoptionProcess.get(), step);
        if (stepDate.isEmpty())
            return null;
        response.setStep(stepDate.get().getStep());
        if (stepDate.get().getDate() != null) // null일 수 있음!
            response.setDate(stepDate.get().getDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        else
            response.setDate(null);
        return response;
    }

    @Override
    @Transactional
    public Boolean postSchedule(Long adoptionProcessId, Timestamp dateTime) {
        Optional<AdoptionProcess> adoptionProcess = infAdoptionProcessRepository.findById(adoptionProcessId);
        if (adoptionProcess.isEmpty())
            return false;
        int step = adoptionProcess.get().getStep(); // 현재 step
        Optional<AdoptionStepDate> stepDate = infAdoptionStepDateRepository.findByAdoptionProcessAndStep(adoptionProcess.get(), step);
        if (stepDate.isEmpty())
            return false;
        stepDate.get().changeDate(dateTime);
        infAdoptionStepDateRepository.save(stepDate.get());
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteSchedule(Long adoptionProcessId) {
        Optional<AdoptionProcess> adoptionProcess = infAdoptionProcessRepository.findById(adoptionProcessId);
        if (adoptionProcess.isPresent()){
            int step = adoptionProcess.get().getStep(); // 현재 step
            List<AdoptionStepDate> adoptionStepDateList = adoptionProcess.get().getAdoptionStepDateList();
            System.out.println("adoptionStepDateList.size() = " + adoptionStepDateList.size());
            for (AdoptionStepDate stepDate: adoptionStepDateList) {
                if(stepDate.getStep() == step){
                    Long stepDateId = stepDate.getAdoptionStepDateId();
                    infAdoptionStepDateRepository.delete(stepDate);
                    return true;
                }
            }
        }
        return false;
    }
}
