package com.mps.qrsent.service.impl;

import com.mps.qrsent.dto.HeadcountDto;
import com.mps.qrsent.model.Headcount;
import com.mps.qrsent.repo.HeadcountRepository;
import com.mps.qrsent.service.HeadCountService;
import com.mps.qrsent.util.CopyUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class HeadCountServiceImpl implements HeadCountService {
    private final ModelMapper modelMapper;
    private final HeadcountRepository headcountRepo;

    @Autowired
    HeadCountServiceImpl(HeadcountRepository headCountRepo) {
        this.modelMapper = new ModelMapper();
        this.headcountRepo = headCountRepo;
    }

    @Override
    public HeadcountDto getHeadCount(Long headCountId) {
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
        // Save the entity
        headcount = headcountRepo.save(headcount);
        // Map from Entity -> DTO
        return modelMapper.map(headcount, HeadcountDto.class);
    }

    @Override
    public HeadcountDto updateHeadCount(HeadcountDto dto, Long headCountId) {
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
    public void deleteHeadCount(Long headCountId) {
        headcountRepo.deleteById(headCountId);
    }
}
