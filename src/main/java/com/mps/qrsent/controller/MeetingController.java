package com.mps.qrsent.controller;

import com.mps.qrsent.dto.MeetingDto;
import com.mps.qrsent.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meeting")
public class MeetingController {
    @Autowired
    MeetingService meetingService;

    @GetMapping("/get-meeting/{meetingId}")
    ResponseEntity<MeetingDto> getMeeting(@PathVariable Long meetingId) {
        return new ResponseEntity<>(meetingService.getMeeting(meetingId), HttpStatus.OK);
    }

    @PostMapping("/add-meeting")
    ResponseEntity<MeetingDto> addMeeting(@RequestBody MeetingDto request) {
        return new ResponseEntity<>(meetingService.addMeeting(request), HttpStatus.CREATED);
    }

    @PutMapping("/update-meeting/{meetingId}")
    ResponseEntity<MeetingDto> updateMeeting(@RequestBody MeetingDto request, @PathVariable Long meetingId) {
        return new ResponseEntity<>(meetingService.updateMeeting(request, meetingId), HttpStatus.OK);
    }

    @DeleteMapping("/delete-meeting/{meetingId}")
    ResponseEntity deleteMeeting(@PathVariable Long meetingId) {
        meetingService.deleteMeeting(meetingId);
        return ResponseEntity.ok().build();
    }
}

