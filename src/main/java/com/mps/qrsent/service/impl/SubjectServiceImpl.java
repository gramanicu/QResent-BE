package com.mps.qrsent.service.impl;

import com.mps.qrsent.dto.MeetingDto;
import com.mps.qrsent.dto.SubjectDto;
import com.mps.qrsent.model.Subject;
import com.mps.qrsent.repo.SubjectRepository;
import com.mps.qrsent.service.SubjectService;
import com.mps.qrsent.util.CopyUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final ModelMapper modelMapper;
    private final SubjectRepository subjectRepo;

    @Autowired
    SubjectServiceImpl(SubjectRepository subjectRepo) {
        this.modelMapper = new ModelMapper();
        this.subjectRepo = subjectRepo;
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
        List<Subject> subjects = subjectRepo.findAll();
        return subjects.stream()
                .map(subject -> modelMapper.map(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDto getSubject(Long subjectId) {
        // Search for the subject, throw exception if not found
        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject does not exist"));
        // Map from Entity -> DTO
        return modelMapper.map(subject, SubjectDto.class);
    }

    @Override
    public SubjectDto addSubject(SubjectDto dto) {
        // Map from DTO -> Entity
        Subject subject = modelMapper.map(dto, Subject.class);
        // Save the entity
        subject = subjectRepo.save(subject);
        // Map from Entity -> DTO
        return modelMapper.map(subject, SubjectDto.class);
    }

    @Override
    public SubjectDto updateSubject(SubjectDto dto, Long subjectId) {
        // Get the current subject
        SubjectDto currentSubject = getSubject(subjectId);
        // Copy all non-null properties from request -> subject
        CopyUtil.copyNonNull(dto, currentSubject);
        // Map from DTO -> Entity and save the updated subject
        Subject updatedSubject = modelMapper.map(currentSubject, Subject.class);
        updatedSubject = subjectRepo.saveAndFlush(updatedSubject);
        // Map from Entity -> DTO
        return modelMapper.map(updatedSubject, SubjectDto.class);
    }

    @Override
    public void deleteSubject(Long subjectId) {
        subjectRepo.deleteById(subjectId);
    }

    @Override
    public List<MeetingDto> getAllMeetingsBySubjectId(Long subjectId) {
        SubjectDto currentSubject = getSubject(subjectId);
        return currentSubject.getMeetings().stream().map(meeting -> modelMapper.map(meeting, MeetingDto.class)).collect(Collectors.toList());
    }
}
