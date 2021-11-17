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
    public List<VerifiedStudent> getActiveStudents(Long meetingId) {
        List<VerifiedStudent> activeStudents = new ArrayList<>();
        Meeting meeting = meetingRepo.getById(meetingId);
        if(meeting.getHeadcounts().isEmpty()) {
            return new ArrayList<>();
        }
        Headcount headcountList1 = meeting.getHeadcounts().get(0);
        if(meeting.getHeadcounts().size() == 1) {
            return headcountList1.getVerifiedStudents();
        }

        for(VerifiedStudent verifiedStudent : headcountList1.getVerifiedStudents()) {
            boolean isActive = true;
            for(int i = 1; i < meeting.getHeadcounts().size(); i++) {
                if(!meeting.getHeadcounts().get(i).getVerifiedStudents().contains(verifiedStudent)) {
                    isActive = false;
                    break;
                }
            }
            if(isActive) {
                activeStudents.add(verifiedStudent);
            }
        }
        return activeStudents;
    }

    @Override
    public List<VerifiedStudent> getPresentStudents(Long meetingId) {
        List<VerifiedStudent> presentStudents = new ArrayList<>();
        Meeting meeting = meetingRepo.getById(meetingId);
        if(meeting.getHeadcounts().isEmpty()) {
            return new ArrayList<>();
        }
        if(meeting.getHeadcounts().size() == 1) {
            return meeting.getHeadcounts().get(0).getVerifiedStudents();
        }
        presentStudents = meeting.getHeadcounts().get(0).getVerifiedStudents();
        for(Headcount headcount : meeting.getHeadcounts()) {
            for(VerifiedStudent verifiedStudent : headcount.getVerifiedStudents()) {
                if(!presentStudents.contains(verifiedStudent)) {
                    presentStudents.add(verifiedStudent);
                }
            }
        }
        return presentStudents;
    }
}
