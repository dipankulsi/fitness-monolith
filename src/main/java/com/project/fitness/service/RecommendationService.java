package com.project.fitness.service;


import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.dto.RecommendationResponse;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.RecommendationRepository;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;


    public RecommendationResponse addRecommendation(RecommendationRequest request) {

        Activity activity = activityRepository.findById(request.getActivityId()).orElseThrow(()-> new RuntimeException("Activity not found"));
        User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new RuntimeException("User not found"));

        Recommendation recommendation = Recommendation.builder().
                type(request.getType()).
                recommendation(request.getRecommendation()).
                improvements(request.getImprovements()).
                safety(request.getSafety()).
                suggestions(request.getSuggestions()).
                user(user).
                activity(activity).
                build();
        Recommendation savedRecommendation = recommendationRepository.save(recommendation);
        return mapToResponse(savedRecommendation);
    }
    private RecommendationResponse mapToResponse(Recommendation savedRecommendation) {

        return RecommendationResponse.builder().
                recommendation(savedRecommendation.getRecommendation()).
                improvements(savedRecommendation.getImprovements()).
                safety(savedRecommendation.getSafety()).
                type(savedRecommendation.getRecommendation()).
                id(savedRecommendation.getId()).
                suggestions(savedRecommendation.getSuggestions()).
                createdAt(savedRecommendation.getCreatedAt()).
                updatedAt(savedRecommendation.getUpdatedAt()).user(savedRecommendation.getUser()).
                activity(savedRecommendation.getActivity()).build();
    }
    public List<RecommendationResponse> getRecommendationsByUserId(String userId) {

        List<Recommendation> recommendations =recommendationRepository.findByUserId(userId);

        return recommendations.stream().map(this::mapToResponse).collect(Collectors.toList());

    }
    public List<RecommendationResponse> getRecommendationsByActivityId(String activityId) {

        List<Recommendation> recommendations =recommendationRepository.findByActivityId(activityId);

        return recommendations.stream().map(this::mapToResponse).collect(Collectors.toList());
    }
}
