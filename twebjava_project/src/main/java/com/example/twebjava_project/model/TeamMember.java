package com.example.twebjava_project.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Team Member", description = "Entire structure of a Team Member")
public class TeamMember {

    private long teamMemberId;
    @NotBlank(message = "Last name cannot be null")
    private String lastName;
    @NotBlank(message = "First name cannot be null")
    private String firstName;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private TeamMemberType type;
    private @Valid Team team;


    public TeamMember(long teamMemberId, @NotBlank(message = "Last name cannot be null") String lastName, @NotBlank(message = "First name cannot be null") String firstName, @Valid Team team) {
        this.teamMemberId = teamMemberId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.team = team;
    }
}
