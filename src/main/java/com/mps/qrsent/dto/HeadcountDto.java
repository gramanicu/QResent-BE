package com.mps.qrsent.dto;

import java.time.LocalDateTime;
import java.util.List;

public class HeadcountDto {

    private String token;
    private LocalDateTime expiresAt;
    private MeetingDto meeting;
    private List<VerifiedStudentDto> verifiedStudents;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public MeetingDto getMeeting() {
        return meeting;
    }

    public void setMeeting(MeetingDto meeting) {
        this.meeting = meeting;
    }

    public List<VerifiedStudentDto> getVerifiedStudents() { return verifiedStudents; }

    public void setVerifiedStudents(List<VerifiedStudentDto> verifiedStudents) { this.verifiedStudents = verifiedStudents; }
}
