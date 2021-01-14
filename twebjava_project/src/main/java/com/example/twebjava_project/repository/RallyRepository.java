package com.example.twebjava_project.repository;

import com.example.twebjava_project.model.Rally;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class RallyRepository {

    private final JdbcTemplate jdbcTemplate;

    public RallyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long add(Rally rally)   {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSql = "INSERT INTO rallies VALUES(NULL, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, new String[] {"rally_id"});
            ps.setString(1, rally.getRallyName().toString());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }



    public Optional findRallyByName(String name) {
        String sql = "select * from rallies where rally_name = ?";

        RowMapper<Rally> mapper = (resultSet, rowNum) ->
                new Rally(resultSet.getLong("rally_id"),
                        resultSet.getString("rally_name"));

        List<Rally> rallies = jdbcTemplate.query(sql, mapper, name);
        if(rallies != null && !rallies.isEmpty()) {
            return Optional.of(rallies.get(0));
        } else {
            return Optional.empty();
        }
    }

    public int changeRallyName(String previousName,String changedName){
        String sql = "update rallies set rally_name=? where rally_name=?";
        return jdbcTemplate.update(sql,changedName,previousName);
    }

    public int deleteRally(String rallyName){
        String sql = "delete from rallies where rally_name=?";
        return jdbcTemplate.update(sql,rallyName);
    }
}
