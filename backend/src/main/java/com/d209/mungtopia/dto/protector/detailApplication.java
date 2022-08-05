package com.d209.mungtopia.dto.protector;

import com.d209.mungtopia.entity.MeetingRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class detailApplication {
    private Long userId;
    private String profileImg;
    private String username;
    private Long chatRoomId;
    private Boolean applicationStatus;
    private Long adoptionProcessId;
    private Long meetingRoomId;
    private Boolean active;
}
