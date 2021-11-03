package com.mps.qrsent.service;


import com.mps.qrsent.dto.MeetingDto;

public interface MeetingService {
    MeetingDto getMeeting(Long meetingId);
    MeetingDto addMeeting(MeetingDto dto);
    MeetingDto updateMeeting(MeetingDto dto, Long meetingId);
    void deleteMeeting(Long meetingId);
}
