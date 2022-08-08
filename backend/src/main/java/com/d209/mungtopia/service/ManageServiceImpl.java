package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.manage.AnswerDto;
import com.d209.mungtopia.dto.manage.ApplicationDto;
import com.d209.mungtopia.dto.manage.ChatDto;
import com.d209.mungtopia.entity.AdoptionStepDate;
import com.d209.mungtopia.entity.Answer;
import com.d209.mungtopia.entity.Application;
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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManageServiceImpl implements ManageService{

    private final ApplicationRepository applicationRepository;
    private final AdoptionStepDateRepository adoptionStepDateRepository;

    @Override
    public ApplicationDto getApplication(Long appId) {
        Application application = applicationRepository.findOne(appId);
        ApplicationDto result = new ApplicationDto(
                application.getName(),
                application.getBirth(),
                application.getGender(),
                application.getJob(),
                application.getZonecode(),
                application.getRoadAddress(),
                application.getDetailAddress(),
                application.getEtc(),
                application.getCreatetime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                application.getApplicationStatus()
        );
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
    public Object getSchedule(Long adoptionStepDateId) {
        return null;
    }

    @Override
    public Boolean postSchedule(Long adoptionStepDateId, LocalDateTime dateTime) {
        AdoptionStepDate stepDate = adoptionStepDateRepository.findOne(adoptionStepDateId);
        stepDate.setDate(dateTime);
        return stepDate.getDate().equals(dateTime);
    }

    @Override
    public Boolean deleteSchedule(Long adoptionStepDateId) {
        AdoptionStepDate stepDate = adoptionStepDateRepository.findOne(adoptionStepDateId);
        stepDate.setDate(null);
        return stepDate.getDate() == null;
    }
}
