package com.example.twebjava_project.controller;

import com.example.twebjava_project.dto.ResultDto;
import com.example.twebjava_project.dto.TeamParticipationResponse;
import com.example.twebjava_project.service.ResultService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/result")
@Api(value = "/result",
        tags = "Result")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping
    @ApiOperation(value = "Create a Result",
            notes = "Create a result of team on a stage")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The result was created"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity add (
            @Valid
            @RequestBody
            @ApiParam(name = "result", value = "Result details", required = true)
                    ResultDto resultDto){

        resultService.add(resultDto);
        return ResponseEntity.ok("The result was added");
    }



    @PutMapping("/{resultId}/{score}")
    @ApiOperation(value = "Update the score of a result",
            notes="Update a score of a result if the resultId is found")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The score was updated"),
            @ApiResponse(code = 404, message = "The result wasn't found")
    })
    public ResponseEntity updateScore (
            @PathVariable
            @ApiParam( value = "Id of target result", required = true)
                    int resultId,
            @PathVariable
            @ApiParam(value = "The value of the score to be added", required = true)
                    int score){

        resultService.updateScore(resultId,score);
        return ResponseEntity.ok("The score was updated");
    }

    @GetMapping("/{editionId}")
    @ApiOperation(value = "Get results",
            notes = "Get results of an edition")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Edition was found"),
            @ApiResponse(code = 404, message = "Edition wasn't found")
    })
    public List<TeamParticipationResponse> getRankingForEdition(
            @PathVariable
            @ApiParam( value = "Id of searched edition", required = true)
                    int editionId){

        return resultService.getRankingForEdition(editionId);
    }


}
