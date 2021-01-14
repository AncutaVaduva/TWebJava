package com.example.twebjava_project.dto;


import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ApiModel(value = "Enroll team to edition", description = "Required details needed to register the team for the edition")
public class EnrollTeamToCompetitionRequest {

    @NotBlank(message = "Rally name cannot be null")
    private String rallyName;
    @NotBlank(message = "Edition name cannot be null")
    private String editionName;
    @NotNull
    private @Valid TeamMemberDto teamMember;
}
