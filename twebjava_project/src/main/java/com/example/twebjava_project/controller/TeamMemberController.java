package com.example.twebjava_project.controller;

import com.example.twebjava_project.model.TeamMember;
import com.example.twebjava_project.service.TeamMemberService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@Validated
@RequestMapping("/teamMember")
@Api(value = "/teamMember",
        tags = "Team Member")
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    public TeamMemberController(TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }

    @PostMapping("/{teamName}")
    @ApiOperation(value = "Create a team member",
            notes = "Creates a new team member on the team with the name received by parameter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Team member was created and added to the team"),
            @ApiResponse(code = 400, message = "Team wasn't found")
    })
    public ResponseEntity add(
            @PathVariable
            @ApiParam( value = "Name of the team", required = true)
                    String teamName,
            @RequestBody
            @ApiParam(value = "Team members details", required = true)
                    TeamMember teamMember){

        return ResponseEntity.created(URI.create("/team/" + teamName))
            .body(teamMemberService.add(teamName,teamMember));
    }
}
