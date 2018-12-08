package com.nsa.team10.asgproject.repositories.implementations;

import com.nsa.team10.asgproject.repositories.daos.*;
import com.nsa.team10.asgproject.repositories.interfaces.IResultsRepository;
import com.nsa.team10.asgproject.services.dtos.NewGSCourseResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ResultsRepository implements IResultsRepository
{
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<GSCourseAttemptDao> courseAttemptMapper;

    //TODO find out the pass mark
    private final double passGSMark = 22;

    @Autowired
    public ResultsRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        courseAttemptMapper = (rs, i) ->
                new GSCourseAttemptDao(
                    rs.getLong("id"),
                    new GSCourseDao(
                        rs.getLong("course_id"),
                        rs.getString("course_number"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        new GSCourseTypeDao(
                                rs.getLong("type_id"),
                                rs.getString("type")
                        ),
                        new GSCourseLocationDao(
                                rs.getLong("location_id"),
                                rs.getString("location")
                        ),
                        new UserDao(
                                rs.getLong("instructor_id"),
                                rs.getString("instructor_forename"),
                                rs.getString("instructor_surname"),
                                rs.getString("instructor_email"),
                                rs.getString("instructor_phone_number"),
                                UserDao.Role.values()[rs.getInt("instructor_role")],
                                rs.getBoolean("instructor_activated"),
                                rs.getBoolean("instructor_disabled"),
                                rs.getString("instructor_created_at"),
                                rs.getString("instructor_updated_at")
                        )
                    ),
                    new CandidateDao(
                        rs.getLong("id"),
                        rs.getString("candidate_number"),
                        new UserDao(
                                rs.getLong("user_id"),
                                rs.getString("forename"),
                                rs.getString("surname"),
                                rs.getString("email"),
                                rs.getString("phone_number"),
                                UserDao.Role.values()[rs.getInt("role")],
                                rs.getBoolean("activated"),
                                rs.getBoolean("disabled"),
                                rs.getString("user_created_at"),
                                rs.getString("user_updated_at")
                        ),
                        null,
                        rs.getString("dob"),
                        null,
                        null,
                        rs.getString("flying_experience"),
                        null,
                        rs.getBoolean("has_payed"),
                        new CandidateProcessStageDao(
                                rs.getLong("stage_id"),
                                rs.getString("stage")
                        )
                    ),
                    rs.getByte("question_bank"),
                    rs.getString("marked_date"),
                    rs.getInt("result")
                );
    }

    @Override
    public void submitGSCourseResults(NewGSCourseResultDto result)
    {
        var sql = "UPDATE ground_school_attempt gsa SET gsa.question_bank = ?, gsa.marked_date = NOW(), gsa.result = ? WHERE gsa.candidate_id = ? AND gsa.ground_school_id = ?;";
        jdbcTemplate.update(sql, result.getQuestionBank(), result.getResult(), result.getCandidateId(), result.getGSCourseId());

        var stageSql = "UPDATE candidate c SET c.stage_id = ? WHERE c.id = ?";

        if (result.getResult() >= passGSMark)
            jdbcTemplate.update(stageSql, CandidateProcessStage.SUBMIT_OP_MANUAL.getStageId(), result.getCandidateId());
    }
}
