package com.example.twebjava_project.mapper;

import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.model.Edition;
import com.example.twebjava_project.model.Rally;
import com.example.twebjava_project.model.Stage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EditionMapper {
    private final StageMapper stageMapper;

    public EditionMapper(StageMapper stageMapper) {
        this.stageMapper = stageMapper;
    }

    public Edition fromDto(EditionDto editionWithStagesDto){
        Edition edition = new Edition();
        Rally rally = new Rally();
        rally.setRallyId(editionWithStagesDto.getRallyId());
        rally.setRallyName(editionWithStagesDto.getRallyName());
        edition.setRally(rally);
        edition.setEditionName(editionWithStagesDto.getEditionName());

        List<Stage> stageList = new ArrayList<>();
        editionWithStagesDto.getStages().forEach(stageDto -> {
            stageList.add(stageMapper.fromDto(stageDto));
        });
        edition.setStages(stageList);
        return edition;
    }
}
