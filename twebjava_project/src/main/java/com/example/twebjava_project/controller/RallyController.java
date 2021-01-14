package com.example.twebjava_project.controller;

import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.model.Rally;
import com.example.twebjava_project.service.RallyService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/rally")
@Api(value = "/rally",
        tags = "Rally")
public class RallyController {

    private final RallyService rallyService;

    public RallyController(RallyService rallyService) {this.rallyService = rallyService; }

    @PostMapping
    @ApiOperation(value = "Create a Rally",
            notes = "Creates a new Rally based on the information received in the request. It also adds Editions if exists")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Rally was added"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity add(
            @Valid
            @RequestBody
            @ApiParam(name = "rally", value = "Rally details", required = true)
                    Rally rally){
        rallyService.add(rally);
        return ResponseEntity.created(URI.create("/rally/" + rally.getRallyName()))
            .body("Rally was added!");
        //return ResponseEntity.ok("Rally was added!");
    }

    @GetMapping("/{rallyName}")
    @ApiOperation(value = "Get rally",
            notes = "Get rally by rally name which is unique")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The rally was found"),
            @ApiResponse(code = 404, message = "The rally wasn't found")
    })
    public List<EditionDto> getRallyEditionsByRallyName(
            @PathVariable
            @ApiParam( value = "Name of target rally", required = true)
                    String rallyName){
        return rallyService.getRallyEditionsByRallyName(rallyName);
    }



    @PutMapping("/changeRallyName/{previousName}/{changedName}")
    @ApiOperation(value = "Change rally name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The name of the rally was changed"),
            @ApiResponse(code = 404, message = "The rally wasn't found")
    })
    public ResponseEntity changeRallyName(
            @PathVariable
            @ApiParam( value = "Previous name of rally", required = true)
                    String previousName,
            @PathVariable
            @ApiParam(name="newName", value = "New name of rally", required = true)
                    String changedName){
        return ResponseEntity.ok(rallyService.changeRallyName(previousName,changedName));
    }



    @DeleteMapping("/{rallyName}")
    @ApiOperation(value = "Delete a rally",
            notes = "Delete a rally with the name received as parameter")
    public ResponseEntity deleteRally(
            @PathVariable
            @ApiParam( value = "Name of target rally", required = true)
                    String rallyName){
        rallyService.deleteRally(rallyName);
        return ResponseEntity.ok("Rally with name "+rallyName+" was deleted!");
    }

}
