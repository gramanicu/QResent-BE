package com.mps.qrsent.service.impl;

import com.mps.qrsent.dto.VerifiedStudentDto;
import com.mps.qrsent.model.VerifiedStudent;
import com.mps.qrsent.repo.VerifiedStudentRepository;
import com.mps.qrsent.service.VerifiedStudentService;
import com.mps.qrsent.util.CopyUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class VerifiedStudentServiceImpl implements VerifiedStudentService {
    private final ModelMapper modelMapper;
    private final VerifiedStudentRepository verifiedStudentRepo;

    @Autowired
    VerifiedStudentServiceImpl(VerifiedStudentRepository verifiedStudentRepo) {
        this.modelMapper = new ModelMapper();
        this.verifiedStudentRepo = verifiedStudentRepo;
    }

    @Override
    public VerifiedStudentDto getVerifiedStudent(Long verifiedStudentId) {
        // Search for the verifiedStudent, throw exception if not found
        VerifiedStudent verifiedStudent = verifiedStudentRepo.findById(verifiedStudentId)
                .orElseThrow(() -> new EntityNotFoundException("VerifiedStudent does not exist"));
        // Map from Entity -> DTO
        return modelMapper.map(verifiedStudent, VerifiedStudentDto.class);
    }

    @Override
    public VerifiedStudentDto addVerifiedStudent(VerifiedStudentDto dto) {
        // Map from DTO -> Entity
        VerifiedStudent verifiedStudent = modelMapper.map(dto, VerifiedStudent.class);
        // Save the entity
        verifiedStudent = verifiedStudentRepo.save(verifiedStudent);
        // Map from Entity -> DTO
        return modelMapper.map(verifiedStudent, VerifiedStudentDto.class);
    }

    @Override
    public VerifiedStudentDto updateVerifiedStudent(VerifiedStudentDto dto, Long verifiedStudentId) {
        // Get the current verifiedStudent
        VerifiedStudentDto currentVerifiedStudent = getVerifiedStudent(verifiedStudentId);
        // Copy all non-null properties from request -> verifiedStudent
        CopyUtil.copyNonNull(dto, currentVerifiedStudent);
        // Map from DTO -> Entity and save the updated verifiedStudent
        VerifiedStudent updatedVerifiedStudent = modelMapper.map(currentVerifiedStudent, VerifiedStudent.class);
        updatedVerifiedStudent = verifiedStudentRepo.saveAndFlush(updatedVerifiedStudent);
        // Map from Entity -> DTO
        return modelMapper.map(updatedVerifiedStudent, VerifiedStudentDto.class);
    }

    @Override
    public void deleteVerifiedStudent(Long verifiedStudentId) {
        verifiedStudentRepo.deleteById(verifiedStudentId);
    }
}
