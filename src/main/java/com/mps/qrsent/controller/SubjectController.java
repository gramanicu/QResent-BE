package com.mps.qrsent.controller;

import com.mps.qrsent.dto.SubjectDto;
import com.mps.qrsent.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping("/add-subject")
    ResponseEntity<SubjectDto> addSubject(@RequestBody SubjectDto request) {
        return new ResponseEntity<>(subjectService.addSubject(request), HttpStatus.CREATED);
    }
}
