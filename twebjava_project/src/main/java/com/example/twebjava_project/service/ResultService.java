package com.example.twebjava_project.service;

import com.example.twebjava_project.dto.ResultDto;
import com.example.twebjava_project.dto.TeamParticipationResponse;
import com.example.twebjava_project.exception.*;
import com.example.twebjava_project.model.Edition;
import com.example.twebjava_project.model.Stage;
import com.example.twebjava_project.model.Team;
import com.example.twebjava_project.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final StageRepository stageRepository;
    private final TeamRepository teamRepository;
    private final EditionRepository editionRepository;
    private final ParticipationRepository participationRepository;

    public ResultService(ResultRepository resultRepository, StageRepository stageRepository, TeamRepository teamRepository, EditionRepository editionRepository, ParticipationRepository participationRepository) {
        this.resultRepository = resultRepository;
        this.stageRepository = stageRepository;
        this.teamRepository = teamRepository;
        this.editionRepository = editionRepository;
        this.participationRepository = participationRepository;
    }

    @Transactional
    public void add (ResultDto resultDto){
        Optional<Stage> stageOptional = stageRepository.findStageById(resultDto.getStageId());
        if (stageOptional.isPresent()){
            Optional<Team> teamOptional = teamRepository.findTeamByName(resultDto.getTeamName());
            if(teamOptional.isPresent()){
                if (participationRepository.checkParticipationByStageAndTeam(resultDto.getStageId(),teamOptional.get().getTeamId())){
                    resultDto.setTeamId((int) teamOptional.get().getTeamId());
                    int count = resultRepository.add(resultDto);
                    if (count == 0)
                        throw new ResultAlreadyRegisteredException(resultDto.getTeamName());
                } else{
                    throw new ParticipationNotFoundException(resultDto.getStageId(),resultDto.getTeamName());
                }

            } else{
                throw new TeamNotFoundException(resultDto.getTeamName());
            }
        } else{
            throw new StageNotFoundException();
        }
    }

    public void updateScore(long participationId, int score){
        int count = resultRepository.updateScore(participationId,score);
        if (count == 0)
            throw new UpdateFailedException("The update couldn't be performed");
    }

    public List<TeamParticipationResponse> getRankingForEdition( int editionId){
        Optional<Edition> editionOptional = editionRepository.findEditionByEditionId(editionId);
        if(editionOptional.isEmpty()){
            throw new EditionNotFoundException(editionId);
        }
        return resultRepository.getRankingForEdition(editionId);
    }
}
