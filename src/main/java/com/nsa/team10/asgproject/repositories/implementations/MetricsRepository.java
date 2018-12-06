package com.nsa.team10.asgproject.repositories.implementations;

import com.nsa.team10.asgproject.repositories.daos.StageMetricsDao;
import com.nsa.team10.asgproject.repositories.interfaces.IMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MetricsRepository implements IMetricsRepository
{
    private final JdbcTemplate jdbcTemplate;
    private static RowMapper<StageMetricsDao> countMapper;

    @Autowired
    public MetricsRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        countMapper = (rs, i) -> new StageMetricsDao(
                rs.getLong("id"),
                rs.getString("stage"),
                rs.getLong("total")
        );
    }

    public List<StageMetricsDao> findStageMetrics()
    {
        var sql = "SELECT s.id,\n" +
                "s.stage,\n" +
                "COUNT(*) as total\n" +
                "FROM candidate c\n" +
                "    JOIN enabled_user u ON u.id = c.user_id\n" +
                "    JOIN candidate_process_stage s ON s.id = c.stage_id\n" +
                "GROUP BY s.id;";
        return jdbcTemplate.query(sql, countMapper);
    }
}
