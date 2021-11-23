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
import com.mps.qrsent.service.AppUserService;
import com.mps.qrsent.service.HeadCountService;
import com.mps.qrsent.util.AppConstants;
import com.mps.qrsent.util.CopyUtil;
import com.mps.qrsent.util.UserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeadCountServiceImpl implements HeadCountService {

    private final VerifiedStudentRepository verifiedStudentRepository;
    private final AppUserService userService;

    private final ModelMapper modelMapper;
    private final HeadcountRepository headcountRepo;

    @Autowired
    HeadCountServiceImpl(VerifiedStudentRepository verifiedStudentRepository, AppUserService userService, HeadcountRepository headCountRepo) {
        this.verifiedStudentRepository = verifiedStudentRepository;
        this.userService = userService;
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

        headcount.setExpiresAt(LocalDateTime.now().plusMinutes(AppConstants.tokenExpiryTimeMinutes));
        headcount.setVerifiedStudents(new ArrayList<>());
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
        AppUser user = userService.getCurrentUser();
        VerifiedStudentDto verifiedStudentDto = new VerifiedStudentDto();
        verifiedStudentDto.setHeadcount(headcount);
        verifiedStudentDto.setAppUser(modelMapper.map(user, AppUserDto.class));
        verifiedStudentDto.setVerifiedAt(LocalDateTime.now());
        if (verifiedStudentDto.getVerifiedAt().isAfter(headcount.getExpiresAt())) {
            throw new IllegalStateException("Expired code");
        }
        verifiedStudentRepository.save(modelMapper.map(verifiedStudentDto, VerifiedStudent.class));
    }

    @Override
    public List<VerifiedStudentDto> getStudentsByHeadCountId(String headCountId) {
        HeadcountDto currentHeadCount = getHeadCount(headCountId);
        return currentHeadCount.getVerifiedStudents().stream().map(student -> modelMapper.map(student, VerifiedStudentDto.class)).collect(Collectors.toList());
    }
}
