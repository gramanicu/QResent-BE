package com.mps.qrsent.controller;

import com.mps.qrsent.dto.AppUserDto;
import com.mps.qrsent.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appuser")
public class AppUserController {
    @Autowired
    AppUserService appUserService;

    @GetMapping("/get-appuser/{appUserId}")
    ResponseEntity<AppUserDto> getAppUser(@PathVariable Long appUserId) {
        return new ResponseEntity<>(appUserService.getAppUser(appUserId), HttpStatus.OK);
    }

    @PostMapping("/add-appuser")
    ResponseEntity<AppUserDto> addAppUser(@RequestBody AppUserDto request) {
        return new ResponseEntity<>(appUserService.addAppUser(request), HttpStatus.CREATED);
    }

    @PutMapping("/update-appuser/{appUserId}")
    ResponseEntity<AppUserDto> updateAppUser(@RequestBody AppUserDto request, @PathVariable Long appUserId) {
        return new ResponseEntity<>(appUserService.updateAppUser(request, appUserId), HttpStatus.OK);
    }

    @DeleteMapping("/delete-appuser/{appUserId}")
    ResponseEntity deleteAppUser(@PathVariable Long appUserId) {
        appUserService.deleteAppUser(appUserId);
        return ResponseEntity.ok().build();
    }
}