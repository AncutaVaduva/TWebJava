package com.example.twebjava_project.service;

import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.dto.EnrollTeamToCompetitionRequest;
import com.example.twebjava_project.dto.TeamDto;
import com.example.twebjava_project.exception.EditionNotFoundException;
import com.example.twebjava_project.exception.ParticipationNotFoundException;
import com.example.twebjava_project.exception.ParticipationAlreadyExistException;
import com.example.twebjava_project.exception.TeamMemberNotFoundException;
import com.example.twebjava_project.model.Edition;
import com.example.twebjava_project.model.TeamMember;
import com.example.twebjava_project.repository.EditionRepository;
import com.example.twebjava_project.repository.ParticipationRepository;
import com.example.twebjava_project.repository.StageRepository;
import com.example.twebjava_project.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final EditionRepository editionRepository;
    private final StageRepository stageRepository;

    public ParticipationService(ParticipationRepository participationRepository, TeamMemberRepository teamMemberRepository, EditionRepository editionRepository, StageRepository stageRepository) {
        this.participationRepository = participationRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.editionRepository = editionRepository;
        this.stageRepository = stageRepository;
    }

    @Transactional
    public void add (EnrollTeamToCompetitionRequest enrollTeamToCompetitionRequest){
        Optional<TeamMember> teamMemberOptional = teamMemberRepository.findTeamMemberByName(enrollTeamToCompetitionRequest.getTeamMember().getLastName(),
                enrollTeamToCompetitionRequest.getTeamMember().getFirstName());
        if (teamMemberOptional.isPresent()) {
            Optional<Edition> optionalEdition = editionRepository.findEditionByRallyAndEditionName(enrollTeamToCompetitionRequest.getRallyName(),
                    enrollTeamToCompetitionRequest.getEditionName());
            if(optionalEdition.isPresent()){
                int count = participationRepository.add(optionalEdition.get().getEditionId(),teamMemberOptional.get().getTeam().getTeamId());
                if (count == 0 )
                    throw new ParticipationAlreadyExistException(
                            teamMemberOptional.get().getTeam().getTeamName(),
                            enrollTeamToCompetitionRequest.getRallyName(),
                            enrollTeamToCompetitionRequest.getEditionName());
            } else {
                throw new EditionNotFoundException(enrollTeamToCompetitionRequest.getRallyName(),
                        enrollTeamToCompetitionRequest.getEditionName());
            }
        } else{
            throw  new TeamMemberNotFoundException(enrollTeamToCompetitionRequest.getTeamMember().getLastName(),
                    enrollTeamToCompetitionRequest.getTeamMember().getFirstName());
        }

    }


    @Transactional
    public void delete (EnrollTeamToCompetitionRequest enrollTeamToCompetitionRequest){
        Optional<TeamMember> teamMemberOptional = teamMemberRepository.findTeamMemberByName(enrollTeamToCompetitionRequest.getTeamMember().getLastName(),
                enrollTeamToCompetitionRequest.getTeamMember().getFirstName());
        if (teamMemberOptional.isPresent()) {
            Optional<Edition> optionalEdition = editionRepository.findEditionByRallyAndEditionName(enrollTeamToCompetitionRequest.getRallyName(),
                    enrollTeamToCompetitionRequest.getEditionName());
            if(optionalEdition.isPresent()){
                int count = participationRepository.delete(optionalEdition.get().getEditionId(),teamMemberOptional.get().getTeam().getTeamId());
                if (count == 0 )
                    throw new ParticipationNotFoundException(
                            teamMemberOptional.get().getTeam().getTeamName(),
                            enrollTeamToCompetitionRequest.getRallyName(),
                            enrollTeamToCompetitionRequest.getEditionName());
            } else {
                throw new EditionNotFoundException(enrollTeamToCompetitionRequest.getRallyName(),
                        enrollTeamToCompetitionRequest.getEditionName());
            }
        } else{
            throw  new TeamMemberNotFoundException(enrollTeamToCompetitionRequest.getTeamMember().getLastName(),
                    enrollTeamToCompetitionRequest.getTeamMember().getFirstName());
        }

    }

    public List<EditionDto> getParticipationsOfATeam(String teamName){
        List<EditionDto> editionDtos =  editionRepository.getEditionsByTeamNameParticipations(teamName);
        editionDtos.forEach(editionDto -> {
            editionDto.setStages(stageRepository.getStages(editionDto.getEditionId()));
        });
        return editionDtos;
    }

    public List<TeamDto> getParticipationsByEditionId(int editionId){
        List<TeamDto> teamDtos = participationRepository.getParticipationsByEditionId(editionId);
        teamDtos.forEach(teamDto -> {
            teamDto.setTeamMembers(teamMemberRepository.getTeamMembersByTeamName(teamDto.getTeamName()));
        });
        return teamDtos;
    }
}
