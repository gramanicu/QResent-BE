package com.mps.qrsent.service;

import com.mps.qrsent.dto.AppUserDto;
import com.mps.qrsent.dto.HeadcountDto;

public interface AppUserService {
    AppUserDto getAppUser(Long appUsertId);
    AppUserDto addAppUser(AppUserDto dto);
    AppUserDto updateAppUser(AppUserDto dto, Long appUsertId);
    void deleteAppUser(Long appUsertId);
}
