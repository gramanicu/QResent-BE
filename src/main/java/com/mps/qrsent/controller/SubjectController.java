package com.mps.qrsent.controller;

import com.mps.qrsent.dto.SubjectDto;
import com.mps.qrsent.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("/get/{subjectId}")
    ResponseEntity<SubjectDto> getSubject(@PathVariable Long subjectId) {
        return new ResponseEntity<>(subjectService.getSubject(subjectId), HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<SubjectDto> addSubject(@RequestBody SubjectDto request) {
        return new ResponseEntity<>(subjectService.addSubject(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{subjectId}")
    ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectDto request, @PathVariable Long subjectId) {
        return new ResponseEntity<>(subjectService.updateSubject(request, subjectId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{subjectId}")
    ResponseEntity deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.ok().build();
    }
}
