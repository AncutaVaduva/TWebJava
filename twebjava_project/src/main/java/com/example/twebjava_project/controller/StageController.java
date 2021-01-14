package com.example.twebjava_project.controller;

import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.dto.StageDto;
import com.example.twebjava_project.service.StageService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/stage")
@Api(value = "/stage",
        tags = "Stage")
public class StageController {

    private final StageService stageService;

    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @PostMapping
    @ApiOperation(value = "Add stages to an edition",
            notes = "Add stages to edition if it exists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Stages were created"),
            @ApiResponse(code = 400, message = "Edition not found")
    })
    public ResponseEntity add(
            @Valid
            @RequestBody
            @ApiParam(name = "edition", value = "Edition with stages details", required = true)
                    EditionDto stagesRequestDto) {

        stageService.add(stagesRequestDto);
        return ResponseEntity.created(URI.create("/stage/" + stagesRequestDto.getRallyName()+ "/" + stagesRequestDto.getEditionName()))
                .body("Stages were added on Edition with editionName="+
                        stagesRequestDto.getEditionName()+" and rallyName="+stagesRequestDto.getRallyName()+"!");
    }


    @GetMapping("/{rallyName}/{editionName}")
    @ApiOperation(value = "Get stages",
            notes = "Get stages of a specified edition from a given rally")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The stages were obtained"),
            @ApiResponse(code = 404, message = "Rally or edition not found")
    })
    public List<StageDto> getStagesByRallyNameAndEditionName(
            @PathVariable
            @ApiParam(value = "Name of target rally", required = true)
                    String rallyName,
            @PathVariable
            @ApiParam(value = "Name of target edition", required = true)
                    String editionName){

        return stageService.getStagesByRallyNameAndEditionName(rallyName,editionName);
    }
}
