package com.mps.qrsent.service;

import com.mps.qrsent.dto.HeadcountDto;
import com.mps.qrsent.dto.SubjectDto;

public interface HeadCountService {
    HeadcountDto getHeadCount(Long headCountId);
    HeadcountDto addHeadCount(HeadcountDto dto);
    HeadcountDto updateHeadCount(HeadcountDto dto, Long headCountId);
    void deleteHeadCount(Long headCountId);
}
