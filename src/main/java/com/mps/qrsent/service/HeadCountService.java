package com.mps.qrsent.service;

import com.mps.qrsent.dto.HeadcountDto;
import com.mps.qrsent.dto.ScanRequestDTO;
import com.mps.qrsent.dto.SubjectDto;
import com.mps.qrsent.dto.VerifiedStudentDto;
import com.mps.qrsent.model.VerifiedStudent;

import java.util.List;

public interface HeadCountService {
    HeadcountDto getHeadCount(String headCountId);
    HeadcountDto addHeadCount(HeadcountDto dto);
    HeadcountDto updateHeadCount(HeadcountDto dto, String headCountId);
    void deleteHeadCount(String headCountId);
    void scanQR(ScanRequestDTO scanRequestDTO);
    List<VerifiedStudentDto> getStudentsByHeadCountId(String headCountId);
}
