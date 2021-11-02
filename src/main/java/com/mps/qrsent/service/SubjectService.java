package com.mps.qrsent.service;

import com.mps.qrsent.dto.SubjectDto;

public interface SubjectService {
    SubjectDto getSubject(Long subjectId);
    SubjectDto addSubject(SubjectDto dto);
    SubjectDto updateSubject(SubjectDto dto, Long subjectId);
    void deleteSubject(Long subjectId);

}
