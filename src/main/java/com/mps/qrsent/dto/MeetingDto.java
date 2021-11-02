package com.mps.qrsent.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingDto {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SubjectDto subject;
    private List<HeadcountDto> headcounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public SubjectDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectDto subject) {
        this.subject = subject;
    }

    public List<HeadcountDto> getHeadcounts() {
        return headcounts;
    }

    public void setHeadcounts(List<HeadcountDto> headcounts) {
        this.headcounts = headcounts;
    }
}
