package com.nsa.team10.asgproject.dal.repositories.implementations;

import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.repositories.interfaces.ICandidateRepository;
import com.nsa.team10.asgproject.services.dtos.NewCandidateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;

@Repository
public class CandidateRepository implements ICandidateRepository
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CandidateRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void create(long userId, NewCandidateDto newCandidate)
    {
        var addressSql = "INSERT INTO address(line_one, line_two, city, county, postcode) VALUES(?, ?, ?, ?, ?);";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(
                    addressSql,
                    new String[] {"id"});
            pstmt.setString(1, newCandidate.getAddress1());
            pstmt.setString(2, newCandidate.getAddress2());
            pstmt.setString(3, newCandidate.getCity());
            pstmt.setString(4, newCandidate.getCounty());
            pstmt.setString(5, newCandidate.getPostcode());
            return pstmt;
        }, holder);
        var addressId = holder.getKey().longValue();

        long companyId = -1;
        if (newCandidate.getCompanyName() != null && !newCandidate.getCompanyName().isEmpty())
        {
            var companySql = "INSERT INTO company(name, phone_number, email) VALUES(?, ?, ?);";
            jdbcTemplate.update(connection -> {
                PreparedStatement pstmt = connection.prepareStatement(
                        companySql,
                        new String[] {"id"});
                pstmt.setString(1, newCandidate.getCompanyName());
                pstmt.setString(2, newCandidate.getCompanyPhone());
                pstmt.setString(3, newCandidate.getCompanyEmail());
                return pstmt;
            }, holder);
            companyId = holder.getKey().longValue();
        }

        var candidateSql = "INSERT INTO candidate(candidate_number, user_id, address_id, company_id, dob, drone_id, prefered_location, has_payed) VALUES('THISWILLBEREPLACED', ?, ?, ?, ?, ?, ?, ?);";

        jdbcTemplate.update(candidateSql, userId, addressId, companyId == -1 ? null : companyId, newCandidate.getDateOfBirth(), newCandidate.getDroneId(), newCandidate.getPreferedLocation(), false);

        var userSql = "UPDATE user SET ROLE = ? WHERE id = ?;";

        jdbcTemplate.update(userSql, UserDao.Role.Candidate.ordinal(), userId);
    }
}
