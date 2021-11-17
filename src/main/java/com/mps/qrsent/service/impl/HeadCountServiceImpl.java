package com.mps.qrsent.service.impl;

import com.mps.qrsent.dto.AppUserDto;
import com.mps.qrsent.dto.HeadcountDto;
import com.mps.qrsent.dto.ScanRequestDTO;
import com.mps.qrsent.dto.VerifiedStudentDto;
import com.mps.qrsent.model.AppUser;
import com.mps.qrsent.model.Headcount;
import com.mps.qrsent.model.VerifiedStudent;
import com.mps.qrsent.repo.HeadcountRepository;
import com.mps.qrsent.repo.VerifiedStudentRepository;
import com.mps.qrsent.service.HeadCountService;
import com.mps.qrsent.util.CopyUtil;
import com.mps.qrsent.util.UserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class HeadCountServiceImpl implements HeadCountService {

    private final VerifiedStudentRepository verifiedStudentRepository;

    private final ModelMapper modelMapper;
    private final HeadcountRepository headcountRepo;

    @Autowired
    HeadCountServiceImpl(VerifiedStudentRepository verifiedStudentRepository, HeadcountRepository headCountRepo) {
        this.verifiedStudentRepository = verifiedStudentRepository;
        this.modelMapper = new ModelMapper();
        this.headcountRepo = headCountRepo;
    }

    @Override
    public HeadcountDto getHeadCount(String headCountId) {
        // Search for the headcount, throw exception if not found
        Headcount headcount = headcountRepo.findById(headCountId)
                .orElseThrow(() -> new EntityNotFoundException("HeadCount does not exist"));
        // Map from Entity -> DTO
        return modelMapper.map(headcount, HeadcountDto.class);
    }

    @Override
    public HeadcountDto addHeadCount(HeadcountDto dto) {
        // Map from DTO -> Entity
        Headcount headcount = modelMapper.map(dto, Headcount.class);

        headcount.setExpiresAt(LocalDateTime.now().plusMinutes(15));

        // Save the entity
        headcount = headcountRepo.save(headcount);
        // Map from Entity -> DTO
        return modelMapper.map(headcount, HeadcountDto.class);
    }

    @Override
    public HeadcountDto updateHeadCount(HeadcountDto dto, String headCountId) {
        // Get the current headcount
        HeadcountDto currentHeadCount = getHeadCount(headCountId);
        // Copy all non-null properties from request -> heacount
        CopyUtil.copyNonNull(dto, currentHeadCount);
        // Map from DTO -> Entity and save the updated headcount
        Headcount updatedHeadCount = modelMapper.map(currentHeadCount, Headcount.class);
        updatedHeadCount = headcountRepo.saveAndFlush(updatedHeadCount);
        // Map from Entity -> DTO
        return modelMapper.map(updatedHeadCount, HeadcountDto.class);
    }

    @Override
    public void deleteHeadCount(String headCountId) {
        headcountRepo.deleteById(headCountId);
    }

    @Override
    public void scanQR(ScanRequestDTO scanRequestDTO) {
        HeadcountDto headcount = getHeadCount(scanRequestDTO.getToken());
        AppUser user = UserUtil.getCurrentUser();
        VerifiedStudentDto verifiedStudentDto = new VerifiedStudentDto();
        verifiedStudentDto.setHeadcount(headcount);
        verifiedStudentDto.setAppUser(modelMapper.map(user, AppUserDto.class));
        verifiedStudentDto.setVerifiedAt(LocalDateTime.now());
        if(verifiedStudentDto.getVerifiedAt().isAfter(headcount.getExpiresAt()))
            throw new IllegalStateException("Expired code");

        verifiedStudentRepository.save(modelMapper.map(verifiedStudentDto, VerifiedStudent.class));
    }
}
