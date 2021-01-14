package com.example.twebjava_project.dto;

import com.example.twebjava_project.model.TeamMemberType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@ApiModel(value = "Team Member Dto", description = "Required details needed to create a new Team Member")
public class TeamMemberDto {

    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private TeamMemberType type;
}
