package com.nsa.team10.asgproject.repositories.implementations;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.CountDao;
import com.nsa.team10.asgproject.repositories.interfaces.IDroneRepository;
import com.nsa.team10.asgproject.repositories.interfaces.IMetricsRepository;
import com.nsa.team10.asgproject.validation.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.CountDao;
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
    public class MetricsRepository implements IMetricsRepository
    {
        private final JdbcTemplate jdbcTemplate;
        private static RowMapper<CountDao> CountMapper;
        private static Map<String, String> orderByCol = new HashMap<>()
        {
            {
                put("id", "id");
                put("count", "COUNT(*)");
//                put("model", "model");
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
        public MetricsRepository(JdbcTemplate jdbcTemplate)
        {
            this.jdbcTemplate = jdbcTemplate;
            CountMapper = (rs, i) -> new CountDao(
                    rs.getLong("id"),
                    rs.getLong("count")
//                    rs.getString("model")
            );
        }

        public List<CountDao> countStages()
        {
            List<CountDao> stages;
            var sql = "SELECT COUNT(*), s.id\n" +
                    "FROM candidate c\n" +
                    "    JOIN enabled_user u ON u.id = c.user_id\n" +
                    "    JOIN candidate_process_stage s ON s.id = c.stage_id\n" +
                    "GROUP BY s.id;";
            stages = jdbcTemplate.query(sql, CountMapper);
            return stages;
        }
}
