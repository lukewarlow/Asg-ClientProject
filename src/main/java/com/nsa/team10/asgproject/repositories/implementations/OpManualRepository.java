package com.nsa.team10.asgproject.repositories.implementations;

import com.nsa.team10.asgproject.repositories.daos.CandidateProcessStage;
import com.nsa.team10.asgproject.repositories.interfaces.IOpManualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OpManualRepository implements IOpManualRepository
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OpManualRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void submit(long candidateId, String absolutePath)
    {
        var submitSql = "INSERT INTO operation_manual_attempt(candidate_id, directory) VALUES(?, ?);";
        jdbcTemplate.update(submitSql, candidateId, absolutePath);

        var stageSql = "UPDATE candidate SET stage_id = ? WHERE id = ?;";
        jdbcTemplate.update(stageSql, CandidateProcessStage.AWAITING_OP_MANUAL_RESULT.getStageId(), candidateId);
    }
}
