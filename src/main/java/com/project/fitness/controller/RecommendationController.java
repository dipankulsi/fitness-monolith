package com.project.fitness.controller;

import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.dto.RecommendationResponse;
import com.project.fitness.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<RecommendationResponse> generate(@RequestBody RecommendationRequest request){

        return new ResponseEntity<>(recommendationService.addRecommendation(request), HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecommendationResponse>> getRecommendationsByUserId(@PathVariable String userId){

        return new ResponseEntity<>(recommendationService.getRecommendationsByUserId(userId),HttpStatus.OK);

    }
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<RecommendationResponse>> getRecommendationsByActivityId(@PathVariable String activityId){

        return new ResponseEntity<>(recommendationService.getRecommendationsByActivityId(activityId),HttpStatus.OK);

    }
}
