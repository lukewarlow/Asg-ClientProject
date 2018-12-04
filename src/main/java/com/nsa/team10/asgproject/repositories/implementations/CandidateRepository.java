package com.nsa.team10.asgproject.repositories.implementations;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.*;
import com.nsa.team10.asgproject.repositories.interfaces.ICandidateRepository;
import com.nsa.team10.asgproject.services.dtos.NewCandidateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CandidateRepository implements ICandidateRepository
{
    private final JdbcTemplate jdbcTemplate;
    private static RowMapper<CandidateDao> candidateMapper;
    private static Map<String, String> orderByCol = new HashMap<>()
    {
        {
            put("id", "id");
            put("candidateNumber", "candidate_number");
            put("forename", "forename");
            put("surname", "surname");
            put("email", "email");
            put("hasPayed", "has_payed");
        }

        /**
         * @param key for column name
         * @return column name otherwise default "id"
         */
        @Override
        public String get(Object key)
        {
            String col = super.get(key);
            return col == null ? "id" : col;
        }
    };

    @Autowired
    public CandidateRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        candidateMapper = (rs, i) ->
        {
            var companyId = rs.getLong("company_id");
            return new CandidateDao(
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
                    new GSCourseLocationDao(
                            rs.getLong("prefered_location"),
                            rs.getString("location")
                    ),
                    rs.getString("dob"),
                    new AddressDao(
                            rs.getLong("address_id"),
                            rs.getString("line_one"),
                            rs.getString("line_two"),
                            rs.getString("city"),
                            rs.getString("county"),
                            rs.getString("postcode")
                    ),
                    companyId == 0 ? null : new CompanyDao(
                            companyId,
                            rs.getString("name"),
                            rs.getString("company_email"),
                            rs.getString("company_phone")
                    ),
                    rs.getString("flying_experience"),
                    new DroneDao(
                            rs.getLong("drone_id"),
                            rs.getString("manufacturer"),
                            rs.getString("model")
                    ),
                    rs.getBoolean("has_payed")
            );

        };
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

        var candidateSql = "INSERT INTO candidate(user_id, address_id, company_id, dob, drone_id, prefered_location, has_payed) VALUES(?, ?, ?, ?, ?, ?, ?);";

        jdbcTemplate.update(candidateSql, userId, addressId, companyId == -1 ? null : companyId, newCandidate.getDateOfBirth(), newCandidate.getDroneId(), newCandidate.getPreferedLocation(), false);

        var userSql = "UPDATE user SET ROLE = ? WHERE id = ?;";

        jdbcTemplate.update(userSql, UserDao.Role.Candidate.ordinal(), userId);
    }

    @Override
    public PaginatedList<CandidateDao> findAll(FilteredPageRequest pageRequest)
    {
        List<CandidateDao> candidates;
        long count;
        var sql = "SELECT ca.id,\n" +
                "ca.candidate_number,\n" +
                "ca.user_id,\n" +
                "u.forename,\n" +
                "u.surname,\n" +
                "u.email,\n" +
                "u.phone_number,\n" +
                "u.role,\n" +
                "u.activated,\n" +
                "u.disabled,\n" +
                "u.created_at AS user_created_at,\n" +
                "u.updated_at AS user_updated_at,\n" +
                "ca.prefered_location,\n" +
                "l.location,\n" +
                "ca.dob,\n" +
                "ca.address_id,\n" +
                "a.line_one,\n" +
                "a.line_two,\n" +
                "a.city,\n" +
                "a.county,\n" +
                "a.postcode,\n" +
                "ca.company_id,\n" +
                "c.name,\n" +
                "c.phone_number AS company_phone,\n" +
                "c.email AS company_email,\n" +
                "ca.flying_experience,\n" +
                "ca.drone_id,\n" +
                "d.manufacturer,\n" +
                "d.model,\n" +
                "ca.has_payed\n" +
                "FROM candidate ca\n" +
                "   JOIN enabled_user u ON u.id = ca.user_id\n" +
                "   JOIN course_location l ON l.id = ca.prefered_location\n" +
                "   JOIN address a ON a.id = ca.address_id\n" +
                "   LEFT JOIN company c ON c.id = ca.company_id\n" +
                "   JOIN drone d ON d.id = ca.drone_id\n" +
                "WHERE ca.candidate_number LIKE ?\n" +
                "ORDER BY " + orderByCol.get(pageRequest.getOrderBy()) + pageRequest.getOrderByAscending() + "\n" +
                "LIMIT ?\n" +
                "OFFSET ?;";
        var params = new Object[]{pageRequest.getSearchTermSql(), pageRequest.getPageSize(), pageRequest.getOffset()};
        candidates = jdbcTemplate.query(sql, params, candidateMapper);
        count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM candidate c JOIN enabled_user u ON u.id = c.user_id WHERE c.candidate_number LIKE ?;", new Object[]{pageRequest.getSearchTermSql()}, Long.class);
        return new PaginatedList<>(candidates, count, pageRequest);
    }

    @Override
    public boolean setHasPayed(long userId, boolean hasPayed)
    {
        var sql = "UPDATE candidate SET has_payed = ? WHERE user_id = ?;";
        var rowsAffected = jdbcTemplate.update(sql, hasPayed, userId);
        return rowsAffected == 1;
    }
}
