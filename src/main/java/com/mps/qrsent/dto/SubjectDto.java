package com.mps.qrsent.dto;

import com.mps.qrsent.util.SubjectType;
import java.util.List;

public class SubjectDto {

    private Long id;
    private String name;
    private SubjectType type;
    private String requirements;
    private String description;
    private List<MeetingDto> meetings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubjectType getType() {
        return type;
    }

    public void setType(SubjectType type) {
        this.type = type;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MeetingDto> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<MeetingDto> meetings) {
        this.meetings = meetings;
    }
}
