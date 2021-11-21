package com.mps.qrsent.controller;

import com.mps.qrsent.dto.AppUserDto;
import com.mps.qrsent.dto.LoginRequestDto;
import com.mps.qrsent.model.AppUser;
import com.mps.qrsent.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AppUserController {
    @Autowired
    AppUserService appUserService;

    private ModelMapper mapper = new ModelMapper();

    @GetMapping("/get-user-details")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<AppUserDto> getCurrentUser() {
        return new ResponseEntity<>(mapper.map(appUserService.getCurrentUser(), AppUserDto.class), HttpStatus.OK);
    }

    @GetMapping("/get-all-users")
    @Secured("ROLE_ADMIN")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<List<AppUserDto>> getAllUsers() {
        return new ResponseEntity<>(appUserService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody AppUserDto request) {
        return new ResponseEntity<>(appUserService.registerUser(request), HttpStatus.OK);
    }

    @GetMapping("/login")
    ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        LoginRequestDto request = new LoginRequestDto(username, password);
        return new ResponseEntity<>(appUserService.authenticate(request), HttpStatus.OK);
    }

    @GetMapping("/refresh")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<String> refresh(@Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(appUserService.refreshToken(token), HttpStatus.OK);
    }

    @PutMapping("/update-user/{appUserId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or principal.username == #username")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    void updateAppUser(@RequestBody AppUserDto request, @PathVariable String username) {
        appUserService.updateUser(request, username);
    }

    @DeleteMapping("/delete-user/{appUserId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or principal.username == #username")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    void deleteAppUser(@PathVariable String appUserId) {
        appUserService.deactivateUser(appUserId);
    }


}