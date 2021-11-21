package com.mps.qrsent.service;

import com.mps.qrsent.dto.AppUserDto;
import com.mps.qrsent.dto.LoginRequestDto;
import com.mps.qrsent.model.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppUserService extends UserDetailsService {
    String registerUser(AppUserDto dto);
    void updateUser(AppUserDto dto, String username);
    void deactivateUser(String appUserId);
    AppUser getCurrentUser();
    List<AppUserDto> getAllUsers();
    String getCurrentUsername();

    String authenticate(LoginRequestDto dto);
    String refreshToken(String currentToken);
}
