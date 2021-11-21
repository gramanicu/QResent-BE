package com.mps.qrsent.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "headcount")
@Entity
public class Headcount {
    @Id
    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @OneToMany(mappedBy = "headcount", orphanRemoval = true)
    private List<VerifiedStudent> verifiedStudents;

    public List<VerifiedStudent> getVerifiedStudents() { return verifiedStudents; }

    public void setVerifiedStudents(List<VerifiedStudent> verifiedStudents) { this.verifiedStudents = verifiedStudents; }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}