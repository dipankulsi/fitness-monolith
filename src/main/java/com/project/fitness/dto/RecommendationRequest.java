package com.project.fitness.dto;

import lombok.Data;
import java.util.List;

@Data
public class RecommendationRequest {


    private String userId;

    private String type;

    private String activityId;

    private String recommendation;

    private List<String> improvements;

    private List<String> suggestions;

    private List<String> safety;


}
