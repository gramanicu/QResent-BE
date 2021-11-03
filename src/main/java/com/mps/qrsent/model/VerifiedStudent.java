package com.mps.qrsent.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "verified_student")
@Entity
public class VerifiedStudent {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "headcount_id", nullable = false)
    private Headcount headcount;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Headcount getHeadcount() {
        return headcount;
    }

    public void setHeadcount(Headcount headcount) {
        this.headcount = headcount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}