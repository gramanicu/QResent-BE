package com.mps.qrsent.controller;

import com.mps.qrsent.dto.HeadcountDto;
import com.mps.qrsent.dto.ScanRequestDTO;
import com.mps.qrsent.dto.VerifiedStudentDto;
import com.mps.qrsent.model.AppUser;
import com.mps.qrsent.repo.HeadcountRepository;
import com.mps.qrsent.service.HeadCountService;
import com.mps.qrsent.util.UserUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/headcount")
public class HeadCountController {
    @Autowired
    HeadCountService headCountService;

    @GetMapping("/get/{headCountId}")
    @RolesAllowed({"ROLE_ADMIN, ROLE_TEACHER"})
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<HeadcountDto> getHeadCount(@PathVariable String headCountId) {
        return new ResponseEntity<>(headCountService.getHeadCount(headCountId), HttpStatus.OK);
    }

    @PostMapping("/add")
    @RolesAllowed({"ROLE_ADMIN, ROLE_TEACHER"})
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<HeadcountDto> addHeadCount(@RequestBody HeadcountDto request) {
        return new ResponseEntity<>(headCountService.addHeadCount(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{headCountId}")
    @RolesAllowed({"ROLE_ADMIN, ROLE_TEACHER"})
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<HeadcountDto> updateHeadCount(@RequestBody HeadcountDto request, @PathVariable String headCountId) {
        return new ResponseEntity<>(headCountService.updateHeadCount(request, headCountId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{headCountId}")
    @RolesAllowed({"ROLE_ADMIN, ROLE_TEACHER"})
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity deleteHeadCount(@PathVariable String headCountId) {
        headCountService.deleteHeadCount(headCountId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/scan-qr")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    void scanQR(@RequestBody ScanRequestDTO scanRequestDTO) {
       headCountService.scanQR(scanRequestDTO);
        
    }

    @GetMapping("/get-students-by-headcountid/{heacCountId}")
    @RolesAllowed({"ROLE_ADMIN, ROLE_TEACHER"})
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<List<VerifiedStudentDto>> getVerifiedStudentsByHeadCountId(@PathVariable String headCountId) {
        return new ResponseEntity<>(headCountService.getStudentsByHeadCountId(headCountId), HttpStatus.OK);
    }



}
