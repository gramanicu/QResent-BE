package com.mps.qrsent.model;

import com.mps.qrsent.util.SubjectType;

import javax.persistence.*;
import java.util.List;

@Table(name = "subject")
@Entity
public class Subject {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SubjectType type;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "subject", orphanRemoval = true)
    private List<Meeting> meetings;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private AppUser professor;

    public AppUser getProfessor() {
        return professor;
    }

    public void setProfessor(AppUser professor) {
        this.professor = professor;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public SubjectType getType() {
        return type;
    }

    public void setType(SubjectType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
