package com.project.fitness.controller;

import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import com.project.fitness.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> addActivity(@RequestBody ActivityRequest request){

        return new ResponseEntity<>(activityService.addActivity(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getAllActivities(@RequestHeader(value = "X-User-ID")  String userId){

        List<ActivityResponse> allActivities = activityService.getAllActivity(userId);
        if(!allActivities.isEmpty())
            return new ResponseEntity<>(allActivities, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
