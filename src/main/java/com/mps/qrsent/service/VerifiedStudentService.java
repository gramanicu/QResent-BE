package com.mps.qrsent.service;

import com.mps.qrsent.dto.VerifiedStudentDto;

public interface VerifiedStudentService {
    VerifiedStudentDto getVerifiedStudent(Long verifiedStudentId);
    VerifiedStudentDto addVerifiedStudent(VerifiedStudentDto dto);
    VerifiedStudentDto updateVerifiedStudent(VerifiedStudentDto dto, Long verifiedStudentId);
    void deleteVerifiedStudent(Long verifiedStudentId);
}
