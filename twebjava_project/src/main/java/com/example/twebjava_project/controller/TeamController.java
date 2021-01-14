package com.example.twebjava_project.controller;

import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.dto.TeamDto;
import com.example.twebjava_project.model.Team;
import com.example.twebjava_project.service.TeamService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping("/team")
@Api(value = "/team",
        tags = "Team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    @ApiOperation(value = "Create a team",
            notes = "Creates a team and if it has team members creates also the members")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Team and team members were created"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity add(
            @Valid
            @RequestBody
            @ApiParam(name = "edition", value = "Team and team members details", required = true)
                    Team team) {

        teamService.add(team);
        return ResponseEntity.created(URI.create("/team/" + team.getTeamName()))
                .body("Team was added");
    }


    @GetMapping("{teamName}")
    @ApiOperation(value = "Get team",
            notes = "Get team with team members by team name which is unique")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The information was get"),
            @ApiResponse(code = 204, message = "Team wasn't found")
    })
    public TeamDto getTeamByName(
            @PathVariable
            @ApiParam( value = "Name of the team", required = true)
                    String teamName) {

        return teamService.getTeamByName(teamName);
    }



    @PutMapping("changeTeamName/{previousName}/{changedName}")
    @ApiOperation(value = "Change team name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The name of the team was changed"),
            @ApiResponse(code = 404, message = "The team wasn't found")
    })
    public ResponseEntity changeTeamName(
            @PathVariable
            @ApiParam( value = "Previous name of team", required = true)
                    String previousName,
            @PathVariable
            @ApiParam(name="newName", value = "New name of rally", required = true)
                    String changedName){

        return ResponseEntity.ok(teamService.changeTeamName(previousName,changedName));
    }

}
