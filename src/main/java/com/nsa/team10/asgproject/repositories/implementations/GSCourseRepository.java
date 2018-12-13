package com.nsa.team10.asgproject.repositories.implementations;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.*;
import com.nsa.team10.asgproject.repositories.interfaces.IGSRepository;
import com.nsa.team10.asgproject.services.dtos.NewGSCourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class GSCourseRepository implements IGSRepository
{
    private final JdbcTemplate jdbcTemplate;
    private static RowMapper<GSCourseDao> gsCourseMapper;
    private static RowMapper<GSCourseTypeDao> typeMapper;
    private static RowMapper<GSCourseLocationDao> locationMapper;

    private static Map<String, String> gsCourseOrderByCol = new HashMap<>()
    {
        {
            put("id", "id");
            put("number", "course_number");
            put("startDate", "start_date");
            put("endDate", "end_date");
            put("location", "location");
        }

        @Override
        public String get(Object key)
        {
            var col = super.get(key);
            return col == null ? "id" : col;
        }
    };
    private static Map<String, String> typeOrderByCol = new HashMap<>()
    {
        {
            put("id", "id");
            put("type", "type");
        }

        @Override
        public String get(Object key)
        {
            var col = super.get(key);
            return col == null ? "id" : col;
        }
    };
    private static Map<String, String> locationOrderByCol = new HashMap<>()
    {
        {
            put("id", "id");
            put("location", "location");
        }

        @Override
        public String get(Object key)
        {
            var col = super.get(key);
            return col == null ? "id" : col;
        }
    };

    @Autowired
    public GSCourseRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        gsCourseMapper = (rs, i) ->
        {
            var instructorId = rs.getLong("instructor_id");
            return new GSCourseDao(
                    rs.getLong("id"),
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
                    instructorId == 0 ? null : new UserDao(
                            instructorId,
                            rs.getString("forename"),
                            rs.getString("surname"),
                            rs.getString("email"),
                            rs.getString("phone_number"),
                            UserDao.Role.values()[rs.getInt("role")],
                            rs.getBoolean("activated"),
                            rs.getBoolean("disabled"),
                            rs.getString("created_at"),
                            rs.getString("updated_at")
                    )
                );
        };
        typeMapper = (rs, i) -> new GSCourseTypeDao(
                rs.getLong("id"),
                rs.getString("type")
        );
        locationMapper = (rs, i) -> new GSCourseLocationDao(
                rs.getLong("id"),
                rs.getString("location")
        );
    }

    @Override
    public void create(NewGSCourseDto newGSCourse)
    {
        var sql = "INSERT INTO ground_school_course(start_date, end_date, type_id, location_id) VALUES(?, ?, ?, ?);";
        jdbcTemplate.update(sql, newGSCourse.getStartDate(), newGSCourse.getEndDate(), newGSCourse.getTypeId(), newGSCourse.getLocationId());
    }

    @Override
    public PaginatedList<GSCourseDao> findAll(FilteredPageRequest pageRequest)
    {
        List<GSCourseDao> courses;
        long count;
        var sql = "SELECT c.id,\n" +
                "c.course_number,\n" +
                "c.start_date,\n" +
                "c.end_date,\n" +
                "c.type_id,\n" +
                "ct.type,\n" +
                "c.location_id,\n" +
                "cl.location,\n" +
                "c.instructor_id,\n" +
                "u.forename,\n" +
                "u.surname,\n" +
                "u.email,\n" +
                "u.phone_number,\n" +
                "u.role,\n" +
                "u.activated,\n" +
                "u.disabled,\n" +
                "u.created_at,\n" +
                "u.updated_at\n" +
                "FROM ground_school_course c\n" +
                "   JOIN course_location cl ON cl.id = c.location_id\n" +
                "   JOIN course_type ct ON ct.id = c.type_id\n" +
                "   LEFT JOIN enabled_user u ON u.id = c.instructor_id\n" +
                "WHERE c.course_number LIKE ?\n" +
                "ORDER BY " + gsCourseOrderByCol.get(pageRequest.getOrderBy()) + pageRequest.getOrderByAscending() + "\n" +
                "LIMIT ?\n" +
                "OFFSET ?;";
        var params = new Object[]{pageRequest.getSearchTermSql(), pageRequest.getPageSize(), pageRequest.getOffset()};
        courses = jdbcTemplate.query(sql, params, gsCourseMapper);
        count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM ground_school_course c WHERE c.course_number LIKE ?;", new Object[] {pageRequest.getSearchTermSql()}, Long.class);
        return new PaginatedList<>(courses, count, pageRequest);
    }

    @Override
    public PaginatedList<GSCourseDao> findAllForInstructor(long instructorId, FilteredPageRequest pageRequest)
    {
        List<GSCourseDao> courses;
        long count;
        var sql = "SELECT c.id,\n" +
                "c.course_number,\n" +
                "c.start_date,\n" +
                "c.end_date,\n" +
                "c.type_id,\n" +
                "ct.type,\n" +
                "c.location_id,\n" +
                "cl.location,\n" +
                "c.instructor_id,\n" +
                "u.forename,\n" +
                "u.surname,\n" +
                "u.email,\n" +
                "u.phone_number,\n" +
                "u.role,\n" +
                "u.activated,\n" +
                "u.disabled,\n" +
                "u.created_at,\n" +
                "u.updated_at\n" +
                "FROM ground_school_course c\n" +
                "   JOIN course_location cl ON cl.id = c.location_id\n" +
                "   JOIN course_type ct ON ct.id = c.type_id\n" +
                "   JOIN enabled_user u ON u.id = c.instructor_id\n" +
                "WHERE c.instructor_id = ? AND c.course_number LIKE ?\n" +
                "ORDER BY " + gsCourseOrderByCol.get(pageRequest.getOrderBy()) + pageRequest.getOrderByAscending() + "\n" +
                "LIMIT ?\n" +
                "OFFSET ?;";
        var params = new Object[]{instructorId, pageRequest.getSearchTermSql(), pageRequest.getPageSize(), pageRequest.getOffset()};
        courses = jdbcTemplate.query(sql, params, gsCourseMapper);
        count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM ground_school_course c JOIN enabled_user u ON u.id = c.instructor_id WHERE c.instructor_id = ? AND c.course_number LIKE ?;", new Object[] {instructorId, pageRequest.getSearchTermSql()}, Long.class);
        return new PaginatedList<>(courses, count, pageRequest);
    }

    @Override
    public Optional<GSCourseDao> findById(long gsCourseId)
    {
        var sql = "SELECT c.id,\n" +
                "c.course_number,\n" +
                "c.start_date,\n" +
                "c.end_date,\n" +
                "c.type_id,\n" +
                "ct.type,\n" +
                "c.location_id,\n" +
                "cl.location,\n" +
                "c.instructor_id,\n" +
                "u.forename,\n" +
                "u.surname,\n" +
                "u.email,\n" +
                "u.phone_number,\n" +
                "u.role,\n" +
                "u.activated,\n" +
                "u.disabled,\n" +
                "u.created_at,\n" +
                "u.updated_at\n" +
                "FROM ground_school_course c\n" +
                "   JOIN course_location cl ON cl.id = c.location_id\n" +
                "   JOIN course_type ct ON ct.id = c.type_id\n" +
                "   LEFT JOIN enabled_user u ON u.id = c.instructor_id\n" +
                "WHERE c.id LIKE ?;";
        return jdbcTemplate.query(sql, new Object[] {gsCourseId}, gsCourseMapper).stream().findFirst();
    }

    @Override
    public PaginatedList<GSCourseTypeDao> findAllTypes(FilteredPageRequest pageRequest)
    {
        List<GSCourseTypeDao> types;
        long count;
        var sql = "SELECT ct.id,\n" +
                "ct.type\n" +
                "FROM course_type ct\n" +
                "WHERE ct.type LIKE ?\n" +
                "ORDER BY " + typeOrderByCol.get(pageRequest.getOrderBy()) + pageRequest.getOrderByAscending() + "\n" +
                "LIMIT ?\n" +
                "OFFSET ?;";
        var params = new Object[]{pageRequest.getSearchTermSql(), pageRequest.getPageSize(), pageRequest.getOffset()};
        types = jdbcTemplate.query(sql, params, typeMapper);
        count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM course_type ct WHERE ct.type LIKE ?;", new Object[] {pageRequest.getSearchTermSql()}, Long.class);
        return new PaginatedList<>(types, count, pageRequest);
    }

    @Override
    public List<GSCourseTypeDao> findAllTypes()
    {
        var sql = "SELECT ct.id, ct.type FROM course_type ct;";
        return jdbcTemplate.query(sql, typeMapper);
    }

    @Override
    public void createLocation(String newLocation)
    {
        var sql = "INSERT INTO course_location(location) VALUES(?);";
        jdbcTemplate.update(sql, newLocation);
    }

    @Override
    public PaginatedList<GSCourseLocationDao> findAllLocations(FilteredPageRequest pageRequest)
    {
        List<GSCourseLocationDao> locations;
        long count;
        var sql = "SELECT cl.id,\n" +
                "cl.location\n" +
                "FROM course_location cl\n" +
                "WHERE cl.location LIKE ?\n" +
                "ORDER BY " + locationOrderByCol.get(pageRequest.getOrderBy()) + pageRequest.getOrderByAscending() + "\n" +
                "LIMIT ?\n" +
                "OFFSET ?;";
        var params = new Object[]{pageRequest.getSearchTermSql(), pageRequest.getPageSize(), pageRequest.getOffset()};
        locations = jdbcTemplate.query(sql, params, locationMapper);
        count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM course_location cl WHERE cl.location LIKE ?;", new Object[] {pageRequest.getSearchTermSql()}, Long.class);
        return new PaginatedList<>(locations, count, pageRequest);
    }

    @Override
    public List<GSCourseLocationDao> findAllLocations()
    {
        var sql = "SELECT cl.id, cl.location FROM course_location cl;";
        return jdbcTemplate.query(sql, locationMapper);
    }

    @Override
    public void assignCandidateToCourse(long gsCourseId, long candidateId)
    {
        var gsAttemptSql = "INSERT INTO ground_school_attempt(ground_school_id, candidate_id) VALUES (?, ?);";

        jdbcTemplate.update(gsAttemptSql, gsCourseId, candidateId);

        var stageSql = "UPDATE candidate SET stage_id = ? WHERE id = ?;";

        jdbcTemplate.update(stageSql, CandidateProcessStage.AWAITING_GS_RESULT.getStageId(), candidateId);
    }

    @Override
    public void assignInstructorToCourse(long gsCourseId, long instructorId)
    {
        var gsAttemptSql = "UPDATE ground_school_course c SET instructor_id = ? WHERE c.id = ?;";
        jdbcTemplate.update(gsAttemptSql, instructorId, gsCourseId);
    }
}
