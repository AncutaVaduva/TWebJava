package com.example.twebjava_project.dto;

import com.example.twebjava_project.model.TeamMember;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Team Dto", description = "Required details needed to create a new Team")
public class TeamDto {

    private String teamName;
    private String countryOfOrigin;
    private List<TeamMemberDto> teamMembers;

    public TeamDto(String teamName, String countryOfOrigin) {
        this.teamName = teamName;
        this.countryOfOrigin = countryOfOrigin;
    }
}
