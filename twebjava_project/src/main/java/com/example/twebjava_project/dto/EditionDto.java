package com.example.twebjava_project.dto;


import com.example.twebjava_project.model.Stage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@ApiModel(value = "Edition Dto", description = "Required details needed to create a new Edition")
public class EditionDto {
    @ApiModelProperty(hidden = true)
    private long rallyId;
    @NotBlank(message = "Rally name cannot be null")
    private String rallyName;
    @ApiModelProperty(hidden = true)
    private long editionId;
    @NotBlank(message = "Edition name cannot be null")
    private String editionName;
    private List<@Valid StageDto> stages;


    public EditionDto(@NotBlank(message = "Rally name cannot be null") String rallyName, @NotBlank(message = "Edition name cannot be null") String editionName, List<@Valid StageDto> stages) {
        this.rallyName = rallyName;
        this.editionName = editionName;
        this.stages = stages;
    }


    public EditionDto(@NotBlank(message = "Rally name cannot be null") String rallyName, @NotBlank(message = "Edition name cannot be null") String editionName) {
        this.rallyName = rallyName;
        this.editionName = editionName;
    }


    public EditionDto( long editionId,@NotBlank(message = "Rally name cannot be null") String rallyName, @NotBlank(message = "Edition name cannot be null") String editionName) {
        this.rallyName = rallyName;
        this.editionId = editionId;
        this.editionName = editionName;
    }
}
