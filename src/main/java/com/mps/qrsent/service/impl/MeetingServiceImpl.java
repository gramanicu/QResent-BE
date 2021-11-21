package com.mps.qrsent.service.impl;


import com.mps.qrsent.dto.MeetingDto;
import com.mps.qrsent.dto.VerifiedStudentDto;
import com.mps.qrsent.model.Headcount;
import com.mps.qrsent.model.Meeting;
import com.mps.qrsent.model.VerifiedStudent;
import com.mps.qrsent.repo.MeetingRepository;
import com.mps.qrsent.service.MeetingService;
import com.mps.qrsent.util.CopyUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingServiceImpl implements MeetingService {
    private final ModelMapper modelMapper;
    private final MeetingRepository meetingRepo;

    @Autowired
    MeetingServiceImpl(MeetingRepository meetingRepo) {
        this.modelMapper = new ModelMapper();
        this.meetingRepo = meetingRepo;
    }

    @Override
    public MeetingDto getMeeting(Long meetingId) {
        // Search for the meeting, throw exception if not found
        Meeting meeting = meetingRepo.findById(meetingId)
                .orElseThrow(() -> new EntityNotFoundException("Meeting does not exist"));
        // Map from Entity -> DTO
        return modelMapper.map(meeting, MeetingDto.class);
    }

    @Override
    public MeetingDto addMeeting(MeetingDto dto) {
        // Map from DTO -> Entity
        Meeting meeting = modelMapper.map(dto, Meeting.class);
        // Save the entity
        meeting = meetingRepo.save(meeting);
        // Map from Entity -> DTO
        return modelMapper.map(meeting, MeetingDto.class);
    }

    @Override
    public MeetingDto updateMeeting(MeetingDto dto, Long meetingId) {
        // Get the current meeting
        MeetingDto currentMeeting = getMeeting(meetingId);
        // Copy all non-null properties from request -> meeting
        CopyUtil.copyNonNull(dto, currentMeeting);
        // Map from DTO -> Entity and save the updated meeting
        Meeting updatedMeeting = modelMapper.map(currentMeeting, Meeting.class);
        updatedMeeting = meetingRepo.saveAndFlush(updatedMeeting);
        // Map from Entity -> DTO
        return modelMapper.map(updatedMeeting, MeetingDto.class);
    }

    @Override
    public void deleteMeeting(Long meetingId) {

        meetingRepo.deleteById(meetingId);
    }

    @Override
    public List<VerifiedStudentDto> getActiveStudents(Long meetingId) {

        List<VerifiedStudent> activeStudents = new ArrayList<>();
        Meeting meeting = meetingRepo.getById(meetingId);

        if (meeting.getHeadcounts().isEmpty()) {
            return new ArrayList<>();
        }

        Headcount firstHeadcount = meeting.getHeadcounts().get(0);

        if(meeting.getHeadcounts().size() == 1) {
            return firstHeadcount.getVerifiedStudents().stream()
                    .map(student -> modelMapper.map(student, VerifiedStudentDto.class))
                    .collect(Collectors.toList());
        }

        for(VerifiedStudent verifiedStudent : firstHeadcount.getVerifiedStudents()) {
            boolean isActive = true;
            for(Headcount headcount : meeting.getHeadcounts()) {
                if(!headcount.getVerifiedStudents().contains(verifiedStudent)) {
                    isActive = false;
                    break;
                }
            }
            if (isActive) {
                activeStudents.add(verifiedStudent);
            }
        }
        return activeStudents.stream()
                .map(student -> modelMapper.map(student, VerifiedStudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VerifiedStudentDto> getPresentStudents(Long meetingId) {
        List<VerifiedStudent> presentStudents = new ArrayList<>();
        Meeting meeting = meetingRepo.getById(meetingId);

        if(meeting.getHeadcounts().isEmpty()) {
            return new ArrayList<>();
        }

        if(meeting.getHeadcounts().size() == 1) {
            return meeting.getHeadcounts().get(0).getVerifiedStudents().stream()
                    .map(student -> modelMapper.map(student, VerifiedStudentDto.class))
                    .collect(Collectors.toList());
        }

        presentStudents = meeting.getHeadcounts().get(0).getVerifiedStudents();

        for(Headcount headcount : meeting.getHeadcounts()) {
            for(VerifiedStudent verifiedStudent : headcount.getVerifiedStudents()) {
                if(!presentStudents.contains(verifiedStudent)) {
                    presentStudents.add(verifiedStudent);
                }
            }
        }
        return presentStudents.stream()
                .map(student -> modelMapper.map(student, VerifiedStudentDto.class))
                .collect(Collectors.toList());
    }
}
