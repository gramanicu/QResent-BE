package com.mps.qrsent.controller;

import com.mps.qrsent.dto.MeetingDto;
import com.mps.qrsent.dto.SubjectDto;
import com.mps.qrsent.service.SubjectService;
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
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("/get/{subjectId}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<SubjectDto> getS(@PathVariable Long subjectId) {
        return new ResponseEntity<>(subjectService.getSubject(subjectId), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<List<SubjectDto>> getAllSubjects() {
        return new ResponseEntity<>(subjectService.getAllSubjects(), HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<SubjectDto> addSubject(@RequestBody SubjectDto request) {
        return new ResponseEntity<>(subjectService.addSubject(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{subjectId}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectDto request, @PathVariable Long subjectId) {
        return new ResponseEntity<>(subjectService.updateSubject(request, subjectId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{subjectId}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-meetings-by-subjectid/{subjectId}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<List<MeetingDto>> getMeetingsBySubjectId(@PathVariable Long subjectId) {
        return new ResponseEntity<>(subjectService.getAllMeetingsBySubjectId(subjectId), HttpStatus.OK);
    }

}
