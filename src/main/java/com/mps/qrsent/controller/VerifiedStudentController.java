package com.mps.qrsent.controller;

import com.mps.qrsent.dto.VerifiedStudentDto;
import com.mps.qrsent.service.VerifiedStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verifiedStudent")
public class VerifiedStudentController {
    @Autowired
    VerifiedStudentService verifiedStudentService;

    @GetMapping("/get/{verifiedStudentId}")
    ResponseEntity<VerifiedStudentDto> getVerifiedStudent(@PathVariable Long verifiedStudentId) {
        return new ResponseEntity<>(verifiedStudentService.getVerifiedStudent(verifiedStudentId), HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<VerifiedStudentDto> addVerifiedStudent(@RequestBody VerifiedStudentDto request) {
        return new ResponseEntity<>(verifiedStudentService.addVerifiedStudent(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{verifiedStudentId}")
    ResponseEntity<VerifiedStudentDto> updateVerifiedStudent(@RequestBody VerifiedStudentDto request, @PathVariable Long verifiedStudentId) {
        return new ResponseEntity<>(verifiedStudentService.updateVerifiedStudent(request, verifiedStudentId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{verifiedStudentId}")
    ResponseEntity deleteVerifiedStudent(@PathVariable Long verifiedStudentId) {
        verifiedStudentService.deleteVerifiedStudent(verifiedStudentId);
        return ResponseEntity.ok().build();
    }
}
