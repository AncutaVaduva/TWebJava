package com.example.twebjava_project.service;


import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.dto.StageDto;
import com.example.twebjava_project.exception.EditionNotFoundException;
import com.example.twebjava_project.exception.RallyNotFoundException;
import com.example.twebjava_project.mapper.StageMapper;
import com.example.twebjava_project.model.Edition;
import com.example.twebjava_project.model.Rally;
import com.example.twebjava_project.model.Stage;
import com.example.twebjava_project.repository.EditionRepository;
import com.example.twebjava_project.repository.RallyRepository;
import com.example.twebjava_project.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    private final StageRepository stageRepository;
    private final EditionRepository editionRepository;
    private final RallyRepository rallyRepository;
    private final StageMapper stageMapper;

    public StageService(StageRepository stageRepository, EditionRepository editionRepository, RallyRepository rallyRepository, StageMapper stageMapper) {
        this.stageRepository = stageRepository;
        this.editionRepository = editionRepository;
        this.rallyRepository = rallyRepository;
        this.stageMapper = stageMapper;
    }


    public void add(EditionDto stagesRequestDto){
        Optional<Edition> editionOptional = editionRepository.findEditionByRallyAndEditionName(stagesRequestDto.getRallyName(),stagesRequestDto.getEditionName());
        if (editionOptional.isPresent()) {
            stagesRequestDto.getStages().forEach(stageDto -> {
                Stage stage =  stageMapper.fromDto(stageDto);
                stage.setEdition(editionOptional.get());
                stageRepository.add(stage);
            });
        } else {
            throw new EditionNotFoundException(stagesRequestDto.getRallyName(),stagesRequestDto.getEditionName());
        }
    }

    public List<StageDto> getStagesByRallyNameAndEditionName(String rallyName, String editionName){
        Optional<Rally> rallyOptional = rallyRepository.findRallyByName(rallyName);
        if (rallyOptional.isPresent()) {
            Optional<Edition> editionOptional = editionRepository.findEditionByRallyAndEditionName(rallyName,editionName);
            if (editionOptional.isPresent()) {
                return stageRepository.getStages(editionOptional.get().getEditionId());
            }else{
                throw new EditionNotFoundException(rallyName,editionName);
            }
        } else {
            throw new RallyNotFoundException(rallyName);
        }
    }
}
