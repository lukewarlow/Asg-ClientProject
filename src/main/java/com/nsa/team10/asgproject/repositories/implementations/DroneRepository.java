package com.nsa.team10.asgproject.repositories.implementations;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.SanitisedSql;
import com.nsa.team10.asgproject.repositories.daos.DroneDao;
import com.nsa.team10.asgproject.repositories.interfaces.IDroneRepository;
import com.nsa.team10.asgproject.validation.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DroneRepository implements IDroneRepository
{
    private final JdbcTemplate jdbcTemplate;
    private static RowMapper<DroneDao> droneMapper;

    @Autowired
    public DroneRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        droneMapper = (rs, i) -> new DroneDao(
                rs.getLong("id"),
                rs.getString("manufacturer"),
                rs.getString("model")
        );
    }

    @Override
    public void create(DroneDao drone) throws ConflictException
    {
        var sql = "INSERT INTO drone (manufacturer, model) VALUES(?, ?);";
        try
        {
            jdbcTemplate.update(sql, drone.getManufacturer(), drone.getModel());
        }
        catch (DataAccessException ex)
        {
            if(ex.getLocalizedMessage().contains("Duplicate entry"))
                throw new ConflictException(DroneDao.class.getTypeName(), "Duplicate entry");
            else
                throw ex;
        }
    }

    @Override
    public PaginatedList<DroneDao> findAll(FilteredPageRequest pageRequest)
    {
        List<DroneDao> drones;
        long count;
        var sqlTemplate = "SELECT d.id,\n" +
                "d.manufacturer,\n" +
                "d.model\n" +
                "FROM drone d\n" +
                "WHERE LOWER(CONCAT(d.manufacturer, ' ', d.model)) LIKE ?\n" +
                "ORDER BY %s \n" +
                "LIMIT ?\n" +
                "OFFSET ?;";

        var sanitisedSql = new SanitisedSql(sqlTemplate, pageRequest.getOrderBy(), pageRequest.getOrderByAscending(), "d", DroneDao.class, "id");
        var params = new Object[]{pageRequest.getSearchTermSql(), pageRequest.getPageSize(), pageRequest.getOffset()};
        drones = jdbcTemplate.query(sanitisedSql.toString(), params, droneMapper);
        count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM drone d WHERE LOWER(CONCAT(d.manufacturer, ' ', d.model)) LIKE ?;", new Object[] {pageRequest.getSearchTermSql()}, Long.class);
        return new PaginatedList<>(drones, count, pageRequest);
    }

    @Override
    public List<DroneDao> search(String search)
    {
        List<DroneDao> drones;
        var sql = "SELECT d.id,\n" +
                "d.manufacturer,\n" +
                "d.model\n" +
                "FROM drone d\n" +
                "WHERE LOWER(CONCAT(d.manufacturer, ' ', d.model)) LIKE ?\n" +
                "LIMIT 8;";
        var params = new Object[]{search.toLowerCase() + "%"};
        drones = jdbcTemplate.query(sql, params, droneMapper);
        return drones;

    }
}
