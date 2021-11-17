package com.mps.qrsent.service;


import com.mps.qrsent.dto.MeetingDto;
import com.mps.qrsent.dto.VerifiedStudentDto;
import com.mps.qrsent.model.VerifiedStudent;

import java.util.List;

public interface MeetingService {
    MeetingDto getMeeting(Long meetingId);
    MeetingDto addMeeting(MeetingDto dto);
    MeetingDto updateMeeting(MeetingDto dto, Long meetingId);
    void deleteMeeting(Long meetingId);
    List<VerifiedStudent> getActiveStudents(Long meetingId);
    List<VerifiedStudent> getPresentStudents(Long meetingId);
}
