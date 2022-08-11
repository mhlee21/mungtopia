package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.manage.ApplicationDto;
import com.d209.mungtopia.dto.manage.ChatDto;
import com.d209.mungtopia.dto.protector.ApplicantProcessRes;

import java.time.LocalDateTime;

public interface ManageService {
    ApplicationDto getApplication(Long appId);
    ChatDto getChatting();
    Object createChat();
    ApplicantProcessRes getSchedule(Long adoptionProcessId);
    Boolean postSchedule(Long adoptionProcessId,  LocalDateTime dateTime);
    Boolean deleteSchedule(Long adoptionProcessId);
}
