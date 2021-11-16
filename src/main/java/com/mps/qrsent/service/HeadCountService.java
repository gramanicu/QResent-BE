package com.mps.qrsent.service;

import com.mps.qrsent.dto.HeadcountDto;
import com.mps.qrsent.dto.ScanRequestDTO;
import com.mps.qrsent.dto.SubjectDto;

public interface HeadCountService {
    HeadcountDto getHeadCount(String headCountId);
    HeadcountDto addHeadCount(HeadcountDto dto);
    HeadcountDto updateHeadCount(HeadcountDto dto, String headCountId);
    void deleteHeadCount(String headCountId);
    void scanQR(ScanRequestDTO scanRequestDTO);
}
