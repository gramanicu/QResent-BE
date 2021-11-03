package com.mps.qrsent.service.impl;

import com.mps.qrsent.dto.AppUserDto;
import com.mps.qrsent.model.AppUser;
import com.mps.qrsent.repo.AppUserRepository;
import com.mps.qrsent.service.AppUserService;
import com.mps.qrsent.util.CopyUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final ModelMapper modelMapper;
    private final AppUserRepository appUserRepo;

    @Autowired
    AppUserServiceImpl(AppUserRepository appUserRepo) {
        this.modelMapper = new ModelMapper();
        this.appUserRepo = appUserRepo;
    }

    @Override
    public AppUserDto getAppUser(Long appUserId) {
        // Search for the AppUser, throw exception if not found
        AppUser appUser = appUserRepo.findById(appUserId)
                .orElseThrow(() -> new EntityNotFoundException("AppUser does not exist"));
        // Map from Entity -> DTO
        return modelMapper.map(appUser, AppUserDto.class);
    }

    @Override
    public AppUserDto addAppUser(AppUserDto dto) {
        // Map from DTO -> Entity
        AppUser appUser = modelMapper.map(dto, AppUser.class);
        // Save the entity
        appUser = appUserRepo.save(appUser);
        // Map from Entity -> DTO
        return modelMapper.map(appUser, AppUserDto.class);
    }

    @Override
    public AppUserDto updateAppUser(AppUserDto dto, Long appUserId) {
        // Get the current appUser
        AppUserDto currentAppUser = getAppUser(appUserId);
        // Copy all non-null properties from request -> appUser
        CopyUtil.copyNonNull(dto, currentAppUser);
        // Map from DTO -> Entity and save the updated appUser
        AppUser updatedAppUser = modelMapper.map(currentAppUser, AppUser.class);
        updatedAppUser = appUserRepo.saveAndFlush(updatedAppUser);
        // Map from Entity -> DTO
        return modelMapper.map(updatedAppUser, AppUserDto.class);
    }

    @Override
    public void deleteAppUser(Long appUserId) {
        appUserRepo.deleteById(appUserId);
    }
}
