package com.mps.qrsent.service;


import com.mps.qrsent.dto.HeadcountDto;
import com.mps.qrsent.dto.MeetingDto;
import com.mps.qrsent.dto.VerifiedStudentDto;
import com.mps.qrsent.model.Headcount;
import com.mps.qrsent.model.VerifiedStudent;

import java.util.List;

public interface MeetingService {
    MeetingDto getMeeting(Long meetingId);
    MeetingDto addMeeting(MeetingDto dto);
    MeetingDto updateMeeting(MeetingDto dto, Long meetingId);
    void deleteMeeting(Long meetingId);
    List<VerifiedStudentDto> getActiveStudents(Long meetingId);
    List<VerifiedStudentDto> getPresentStudents(Long meetingId);
    List<HeadcountDto> getAllHeadcountsByMeetingId(Long meetingId);

}
