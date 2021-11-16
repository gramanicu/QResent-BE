package com.mps.qrsent.controller;

import com.mps.qrsent.dto.HeadcountDto;
import com.mps.qrsent.dto.ScanRequestDTO;
import com.mps.qrsent.dto.VerifiedStudentDto;
import com.mps.qrsent.model.AppUser;
import com.mps.qrsent.repo.HeadcountRepository;
import com.mps.qrsent.service.HeadCountService;
import com.mps.qrsent.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/headcount")
public class HeadCountController {
    @Autowired
    HeadCountService headCountService;

    @GetMapping("/get-headcount/{headCountId}")
    ResponseEntity<HeadcountDto> getHeadCount(@PathVariable String headCountId) {
        return new ResponseEntity<>(headCountService.getHeadCount(headCountId), HttpStatus.OK);
    }

    @PostMapping("/add-headcount")
    ResponseEntity<HeadcountDto> addHeadCount(@RequestBody HeadcountDto request) {
        return new ResponseEntity<>(headCountService.addHeadCount(request), HttpStatus.CREATED);
    }

    @PutMapping("/update-headcount/{headCountId}")
    ResponseEntity<HeadcountDto> updateHeadCount(@RequestBody HeadcountDto request, @PathVariable String headCountId) {
        return new ResponseEntity<>(headCountService.updateHeadCount(request, headCountId), HttpStatus.OK);
    }

    @DeleteMapping("/delete-headcount/{headCountId}")
    ResponseEntity deleteHeadCount(@PathVariable String headCountId) {
        headCountService.deleteHeadCount(headCountId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/scanQRcode")
    void scanQR(@RequestBody ScanRequestDTO scanRequestDTO) {
       headCountService.scanQR(scanRequestDTO);
        
    }


}
