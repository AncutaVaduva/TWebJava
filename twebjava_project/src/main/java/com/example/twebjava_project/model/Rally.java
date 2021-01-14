package com.example.twebjava_project.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ApiModel(value = "Rally", description = "Required details needed to create a new Rally")
public class Rally {

    private long rallyId;
    @NotBlank(message = "Rally name cannot be null")
    private String rallyName;
    List<@Valid Edition> editions;


    public Rally(long rallyId, @NotBlank(message = "Rally name cannot be null") String rallyName) {
        this.rallyId = rallyId;
        this.rallyName = rallyName;
    }

}
