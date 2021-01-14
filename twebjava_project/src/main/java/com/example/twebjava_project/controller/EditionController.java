package com.example.twebjava_project.controller;

import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.repository.RallyRepository;
import com.example.twebjava_project.service.EditionService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/edition")
@Api(value = "/edition",
    tags = "Edition")
public class EditionController {

    private final EditionService editionService;
    private final RallyRepository rallyRepository;

    public EditionController(EditionService editionService, RallyRepository rallyRepository) {
        this.editionService = editionService;
        this.rallyRepository = rallyRepository;
    }

    @PostMapping
    @ApiOperation(value = "Create an edition",
            notes = "Creates a new Edition based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Edition was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity add(
            @Valid
            @RequestBody
            @ApiParam(name = "edition", value = "Edition details", required = true)
                    EditionDto editionWithStagesDto){

        return ResponseEntity.created(URI.create("/edition/" + editionWithStagesDto.getRallyName()+"/"+editionWithStagesDto.getEditionName()))
                .body(editionService.add(editionWithStagesDto));
    }



    @GetMapping("/{rallyName}/{editionName}")
    @ApiOperation(value = "Get an edition",
            notes = "Get edition which have the specified name and belongs to the rally with name specified in the first parameter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The Edition was found")
    })
    public EditionDto getEditionByRallyAndEditionName(
            @PathVariable
            @ApiParam(name = "rally name", value = "Name of target rally", required = true)
                    String rallyName,
            @PathVariable
            @ApiParam(name = "edition name", value = "Name of target edition", required = true)
                    String editionName){

        return editionService.getEditionByRallyAndEditionName(rallyName,editionName);
    }




    @GetMapping("/{editionName}")
    @ApiOperation(value = "Get editions",
            notes = "Get editions which have the name received from parameter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The Editions were found")
    })
    public List<EditionDto> get(
            @PathVariable
            @ApiParam(name = "edition name", value = "Name of target edition", required = true)
                    String editionName){

        return editionService.getEditionsByEditionName(editionName);
    }


}
