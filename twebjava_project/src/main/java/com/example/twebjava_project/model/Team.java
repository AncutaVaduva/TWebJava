package com.example.twebjava_project.model;

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
@ApiModel(value = "Team", description = "Entire structure of a Team")
public class Team {

    private long teamId;
    @NotBlank(message = "Team name cannot be null")
    private String teamName;
    @NotNull
    private String countryOfOrigin;
    private List<@Valid TeamMember> teamMembers;

    public Team(long teamId, @NotBlank(message = "Team name cannot be null") String teamName, @NotNull String countryOfOrigin) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.countryOfOrigin = countryOfOrigin;
    }

    public Team(long teamId, String teamName){
        this.teamId= teamId;
        this.teamName = teamName;
    }
}
