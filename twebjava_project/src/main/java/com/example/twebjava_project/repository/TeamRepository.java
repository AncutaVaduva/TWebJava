package com.example.twebjava_project.repository;

import com.example.twebjava_project.model.Rally;
import com.example.twebjava_project.model.Team;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepository {

    private final JdbcTemplate jdbcTemplate;

    public TeamRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long add(Team team){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSql = "INSERT INTO teams VALUES(NULL, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, new String[] {"stage_id"});
            ps.setString(1, team.getTeamName());
            ps.setString(2, team.getCountryOfOrigin());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Optional findTeamByName(String teamName) {
        String sql = "select * from teams where team_name = ?";

        RowMapper<Team> mapper = (resultSet, rowNum) ->
                new Team(resultSet.getLong("team_id"),
                        resultSet.getString("team_name"),
                        resultSet.getString("country_of_origin"));

        List<Team> teams = jdbcTemplate.query(sql, mapper, teamName);
        if(teams != null && !teams.isEmpty()) {
            return Optional.of(teams.get(0));
        } else {
            return Optional.empty();
        }
    }

    public int changeTeamName(String previousName,String changedName){
        String sql = "update teams set team_name=? where team_name=?";
        return jdbcTemplate.update(sql,changedName,previousName);
    }
}
