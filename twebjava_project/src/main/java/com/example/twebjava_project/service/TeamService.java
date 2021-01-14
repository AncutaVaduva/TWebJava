package com.example.twebjava_project.service;

import com.example.twebjava_project.dto.TeamDto;
import com.example.twebjava_project.exception.TeamNotFoundException;
import com.example.twebjava_project.exception.UpdateFailedException;
import com.example.twebjava_project.model.Team;
import com.example.twebjava_project.model.TeamMember;
import com.example.twebjava_project.repository.TeamMemberRepository;
import com.example.twebjava_project.repository.TeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    public TeamService(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository) {
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    @Transactional
    public void add(Team team){
        long teamId = teamRepository.add(team);
        if(team.getTeamMembers()!= null && team.getTeamMembers().size()!=0){
            team.setTeamId(teamId);
            team.getTeamMembers().forEach(teamMember -> {
                teamMemberRepository.add(teamMember,team);
            });
        }
    }

    public TeamDto getTeamByName(String teamName) {
        Optional<Team> teamOptional = teamRepository.findTeamByName(teamName);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            TeamDto teamDto = new TeamDto(team.getTeamName(), team.getCountryOfOrigin(), teamMemberRepository.getTeamMembersByTeamName(teamName));
            return teamDto;
        } else{
            throw new TeamNotFoundException(teamName);
        }
    }

    @Transactional
    public TeamDto changeTeamName(String previousName,String changedName){
        Optional<Team> teamOptional = teamRepository.findTeamByName(previousName);
        if (teamOptional.isPresent()) {
            int result = teamRepository.changeTeamName(previousName,changedName);
            if(result == 1){
                Optional<Team> teamOptionalAfterUpdate = teamRepository.findTeamByName(changedName);
                Team team = teamOptionalAfterUpdate.get();
                TeamDto teamDto = new TeamDto(team.getTeamName(), team.getCountryOfOrigin(), teamMemberRepository.getTeamMembersByTeamName(team.getTeamName()));
                return teamDto;
            }
            else{
                throw new UpdateFailedException("Team name could not be updated");
            }
        }
        else{
            throw new TeamNotFoundException(previousName);
        }
    }
}
