package com.mps.qrsent.service;

import com.mps.qrsent.dto.MeetingDto;
import com.mps.qrsent.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> getAllSubjects();
    SubjectDto getSubject(Long subjectId);
    SubjectDto addSubject(SubjectDto dto);
    SubjectDto updateSubject(SubjectDto dto, Long subjectId);
    void deleteSubject(Long subjectId);
    List<MeetingDto> getAllMeetingsBySubjectId(Long subjectId);
}
