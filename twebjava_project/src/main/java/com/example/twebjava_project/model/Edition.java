package com.example.twebjava_project.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Edition", description = "Entire structure of an Edition")
public class Edition {

    @ApiModelProperty(hidden = true)
    @ApiParam(hidden = true)
    private long editionId;
    @NotBlank(message = "Edition name cannot be null")
    @ApiModelProperty(value = "editionName", required = true, notes = "The name of the Edition")
    private String editionName;
    private @Valid Rally rally;
    private List<@Valid Stage> stages;

    public Edition(@NotBlank(message = "Edition name cannot be null") String editionName, List<@Valid Stage> stages) {
        this.editionName = editionName;
        this.stages = stages;
    }

    public Edition(long editionId) {
        this.editionId = editionId;
    }


    public Edition(String editionName){
        this.editionName = editionName;
    }

    public Edition(long editionId, @NotBlank(message = "Edition name cannot be null") String editionName) {
        this.editionId = editionId;
        this.editionName = editionName;
    }

}
