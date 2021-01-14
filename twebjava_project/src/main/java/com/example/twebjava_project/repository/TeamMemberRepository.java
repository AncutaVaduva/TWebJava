package com.example.twebjava_project.repository;

import com.example.twebjava_project.dto.TeamMemberDto;
import com.example.twebjava_project.model.Rally;
import com.example.twebjava_project.model.Team;
import com.example.twebjava_project.model.TeamMember;
import com.example.twebjava_project.model.TeamMemberType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TeamMemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public TeamMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional findTeamMemberByName(String lastName, String firstName) {
        String sql = "select member_id, last_name, first_name, tm.team_id, team_name from team_members tm, teams t where last_name = ? and first_name=? and tm.team_id=t.team_id";

        RowMapper<TeamMember> mapper = (resultSet, rowNum) ->
                new TeamMember(resultSet.getLong("member_id"),
                        resultSet.getString("last_name"),
                        resultSet.getString("first_name"),
                        new Team(resultSet.getLong("team_id"), resultSet.getString("team_name")));

        List<TeamMember> teamMembers = jdbcTemplate.query(sql, mapper, lastName,firstName);
        if(teamMembers != null && !teamMembers.isEmpty()) {
            return Optional.of(teamMembers.get(0));
        } else {
            return Optional.empty();
        }
    }

    public void add(TeamMember teamMember, Team team){
        String sql = "INSERT INTO team_members VALUES (NULL, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, teamMember.getLastName(),teamMember.getFirstName(),teamMember.getDateOfBirth(),
                teamMember.getType().toString(),team.getTeamId());
    }

    public List<TeamMemberDto> getTeamMembersByTeamName(String teamName) {
        String sql = "select * from team_members tm, teams t where tm.team_id=t.team_id and team_name= ?";
        List<Map<String, Object>> teamMembers = jdbcTemplate.queryForList(sql,teamName);
        List<TeamMemberDto> teamMemberDtoList = new ArrayList<>();
        teamMembers.forEach(member ->{
            TeamMemberDto teamMemberDto = new TeamMemberDto();
            teamMemberDto.setLastName((String) member.get("last_name"));
            teamMemberDto.setFirstName((String) member.get("first_name"));
            teamMemberDto.setDateOfBirth( ((Date) member.get("date_of_birth")).toLocalDate());
            teamMemberDto.setType(TeamMemberType.valueOf((String) member.get("type")));
            teamMemberDtoList.add(teamMemberDto);
        });
        return teamMemberDtoList;
    }
}
