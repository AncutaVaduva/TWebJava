package com.example.twebjava_project.dto;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Participation", description = "Returned values for a participation")
public class TeamParticipationResponse {

    private String teamName;
    private String countryOfOrigin;
    private int totalScore;
}
