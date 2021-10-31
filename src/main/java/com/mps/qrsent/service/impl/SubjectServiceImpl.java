package com.mps.qrsent.service.impl;

import com.mps.qrsent.dto.SubjectDto;
import com.mps.qrsent.model.Subject;
import com.mps.qrsent.repo.SubjectRepository;
import com.mps.qrsent.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SubjectDto addSubject(SubjectDto dto) {
        Subject subject = modelMapper.map(dto, Subject.class);
        return modelMapper.map(subjectRepo.save(subject), SubjectDto.class);
    }
}
