package com.project.fitness.service;

import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import com.project.fitness.model.Activity;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;


    public ActivityResponse addActivity(ActivityRequest activity) {
        User user = userRepository.findById(activity.getUserId()).orElseThrow(
                ()-> new RuntimeException("User not found"));

        Activity newActivity = Activity.builder().activityType(activity.getActivityType()).caloriesBurned(activity.getCaloriesBurned())
                 .duration(activity.getDuration()).startTime(activity.getStartTime()).user(user).
                 additionalMetrics(activity.getAdditionalMetrics()).build();

        Activity storedActivity = activityRepository.save(newActivity);
        return mapResponse(storedActivity);

    }

    public List<ActivityResponse> getAllActivity(String userId) {

        List<Activity> activities = activityRepository.findByUserId(userId);

        return activities.stream().map(this::mapResponse).collect(Collectors.toList());

    }

        public ActivityResponse mapResponse(Activity activity) {

            return ActivityResponse.builder().id(activity.getId()).activityType(activity.getActivityType()).createdAt(activity.getCreatedAt())
                    .caloriesBurned(activity.getCaloriesBurned()).startTime(activity.getStartTime()).updatedAt(activity.getUpdatedAt()).duration(activity.getDuration())
                    .userId(activity.getUser().getId()).additionalMetrics(activity.getAdditionalMetrics()).build();
        }

}