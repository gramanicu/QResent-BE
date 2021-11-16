package com.mps.qrsent.dto;

import java.time.LocalDateTime;

public class VerifiedStudentDto {


    private Long id;
    private HeadcountDto headcount;
    private AppUserDto appUser;
    private LocalDateTime verifiedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HeadcountDto getHeadcount() {
        return headcount;
    }

    public void setHeadcount(HeadcountDto headcount) {
        this.headcount = headcount;
    }

    public AppUserDto getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUserDto appUser) {
        this.appUser = appUser;
    }

    public LocalDateTime getVerifiedAt() { return verifiedAt; }

    public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }
}
