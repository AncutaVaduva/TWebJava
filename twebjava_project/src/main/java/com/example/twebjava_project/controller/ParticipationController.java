package com.example.twebjava_project.controller;

import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.dto.EnrollTeamToCompetitionRequest;
import com.example.twebjava_project.dto.TeamDto;
import com.example.twebjava_project.service.ParticipationService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/participation")
@Api(value = "/participation",
        tags = "Participation")
public class ParticipationController {

    private final ParticipationService participationService;

    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @PostMapping
    @ApiOperation(value = "Create a Participation",
            notes = "Enroll the current user's team in a rally edition")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The team of the team member was successfully registered at rally edition received in the request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity add(
             @Valid
             @RequestBody
             @ApiParam(name = "enroll", value = "Edition and user details", required = true)
             EnrollTeamToCompetitionRequest enrollTeamToCompetitionRequest){

        participationService.add(enrollTeamToCompetitionRequest);
        return ResponseEntity.ok("The team of the team member was successfully registered at rally with name "+
                enrollTeamToCompetitionRequest.getRallyName()+" and edition name "+
                enrollTeamToCompetitionRequest.getEditionName());
    }



    @DeleteMapping
    @ApiOperation(value = "Delete a Participation",
            notes = "Unregister the current user's team from a rally edition")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The team of the team member was successfully unregistered at rally edition received in the request"),
            @ApiResponse(code = 404, message = "The team member or edition you are looking for wasn't found")
    })
    public ResponseEntity unregisterTeam(
             @Valid
             @RequestBody
             @ApiParam(name = "enroll", value = "Edition and user details", required = true)
                     EnrollTeamToCompetitionRequest enrollTeamToCompetitionRequest){

        participationService.delete(enrollTeamToCompetitionRequest);
        return ResponseEntity.ok("The team of the team member was successfully unregistered at rally with name "+
                enrollTeamToCompetitionRequest.getRallyName()+" and edition name "+
                enrollTeamToCompetitionRequest.getEditionName());
    }



    @GetMapping("/{teamName}")
    @ApiOperation(value = "Get team participation",
            notes = "Get all the participation of a team regardless of edition and rally")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The team was found"),
            @ApiResponse(code = 404, message = "The team wasn't found")
    })
    public List<EditionDto> getParticipationsOfATeam(
            @PathVariable
            @ApiParam(value = "Name of the team", required = true)
                    String teamName){

        return participationService.getParticipationsOfATeam(teamName);
    }


    @GetMapping("/editionParticipations/{editionId}")
    @ApiOperation(value = "Get participation on edition",
            notes = "Get all teams participating in the edition whose id is received as a parameter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The edition was found"),
            @ApiResponse(code = 404, message = "The edition wasn't found")
    })
    public List<TeamDto> getParticipationsByEditionId(
            @PathVariable
            @ApiParam( value = "Id of the target Edition", required = true)
                    int editionId){

        return participationService.getParticipationsByEditionId(editionId);
    }


}
