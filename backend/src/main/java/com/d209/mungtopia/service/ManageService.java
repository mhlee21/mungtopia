package com.d209.mungtopia.service;

import com.d209.mungtopia.dto.manage.ApplicationDto;
import com.d209.mungtopia.dto.manage.ChatDto;

import java.time.LocalDateTime;

public interface ManageService {
    ApplicationDto getApplication(Long appId);
    ChatDto getChatting();
    Object createChat();
    Object getSchedule(Long Id);
    Boolean postSchedule(Long Id,  LocalDateTime dateTime);
    Boolean deleteSchedule(Long Id);
}
