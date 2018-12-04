package com.nsa.team10.asgproject.repositories.implementations;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.UserDao;
import com.nsa.team10.asgproject.repositories.daos.UserWithPasswordDao;
import com.nsa.team10.asgproject.repositories.interfaces.IUserRepository;
import com.nsa.team10.asgproject.validation.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository
{
    private final JdbcTemplate jdbcTemplate;
    private static RowMapper<UserWithPasswordDao> userWithPasswordMapper;
    private static RowMapper<UserDao> userMapper;
    private static Map<String, String> orderByCol = new HashMap<>()
    {
        {
            put("id", "id");
            put("forename", "forename");
            put("surname", "surname");
            put("email", "email");
            put("phoneNumber", "phone_number");
            put("role", "role");
            put("activated", "activated");
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
    public UserRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;

        userWithPasswordMapper = (rs, i) -> new UserWithPasswordDao(
            rs.getLong("id"),
            rs.getString("forename"),
            rs.getString("surname"),
            rs.getString("email"),
            rs.getString("phone_number"),
            UserDao.Role.values()[rs.getInt("role")],
            rs.getBoolean("activated"),
            rs.getBoolean("disabled"),
            rs.getString("created_at"),
            rs.getString("updated_at"),
            rs.getString("password")
        );
        userMapper = (rs, i) -> new UserDao(
            rs.getLong("id"),
            rs.getString("forename"),
            rs.getString("surname"),
            rs.getString("email"),
            rs.getString("phone_number"),
            UserDao.Role.values()[rs.getInt("role")],
            rs.getBoolean("activated"),
            rs.getBoolean("disabled"),
            rs.getString("created_at"),
            rs.getString("updated_at")
        );
    }

    public void create(UserDao newUser, String hashedPassword) throws ConflictException
    {
        var sql = "INSERT INTO user (forename, surname, email, phone_number, role, password) VALUES(?,?,?,?,?,?);";
        try
        {
            jdbcTemplate.update(sql, newUser.getForename(), newUser.getSurname(), newUser.getEmail(), newUser.getPhoneNumber(), newUser.getRole().ordinal(), hashedPassword);
        }
        catch (DataAccessException ex)
        {
          if(ex.getLocalizedMessage().contains("for key 'email'"))
            throw new ConflictException(UserDao.class.getTypeName(), "Email address already in use.");
          if(ex.getLocalizedMessage().contains("for key 'phone_number'"))
            throw new ConflictException(UserDao.class.getTypeName(), "Phone number already in use.");
          else throw ex;
        }
    }

    @Override
    public PaginatedList<UserDao> findAll(FilteredPageRequest pageRequest)
    {
        List<UserDao> users;
        long count;
        var sql = "SELECT u.id,\n" +
                "u.forename,\n" +
                "u.surname,\n" +
                "u.email,\n" +
                "u.phone_number,\n" +
                "u.role,\n" +
                "u.activated,\n" +
                "u.disabled,\n" +
                "u.created_at,\n" +
                "u.updated_at\n" +
                "FROM enabled_user u\n" +
                "WHERE u.forename LIKE ?\n" +
                "ORDER BY " + orderByCol.get(pageRequest.getOrderBy()) + pageRequest.getOrderByAscending() + "\n" +
                "LIMIT ?\n" +
                "OFFSET ?;";
        var params = new Object[]{pageRequest.getSearchTermSql(), pageRequest.getPageSize(), pageRequest.getOffset()};
        users = jdbcTemplate.query(sql, params, userMapper);
        count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM enabled_user WHERE forename LIKE ?;", new Object[]{pageRequest.getSearchTermSql()}, Long.class);
        return new PaginatedList<>(users, count, pageRequest);
    }

    @Override
    public PaginatedList<UserDao> findAllDisabled(FilteredPageRequest pageRequest)
    {
        List<UserDao> users;
        long count;
        var sql = "SELECT u.id,\n" +
                "u.forename,\n" +
                "u.surname,\n" +
                "u.email,\n" +
                "u.phone_number,\n" +
                "u.role,\n" +
                "u.activated,\n" +
                "u.disabled,\n" +
                "u.created_at,\n" +
                "u.updated_at\n" +
                "FROM user u\n" +
                "WHERE u.forename LIKE ?\n" +
                "AND u.disabled = TRUE\n" +
                "ORDER BY " + orderByCol.get(pageRequest.getOrderBy()) + pageRequest.getOrderByAscending() + "\n" +
                "LIMIT ?\n" +
                "OFFSET ?;";
        var params = new Object[]{pageRequest.getSearchTermSql(), pageRequest.getPageSize(), pageRequest.getOffset()};
        users = jdbcTemplate.query(sql, params, userMapper);
        count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user WHERE forename LIKE ? AND disabled = TRUE;", new Object[]{pageRequest.getSearchTermSql()}, Long.class);
        return new PaginatedList<>(users, count, pageRequest);
    }

    public Optional<UserWithPasswordDao> findWithPasswordByEmail(String email)
    {
        var sql = "SELECT u.id,\n" +
                "u.forename,\n" +
                "u.surname,\n" +
                "u.email,\n" +
                "u.phone_number,\n" +
                "u.role,\n" +
                "u.activated,\n" +
                "u.disabled,\n" +
                "u.created_at,\n" +
                "u.updated_at,\n" +
                "u.password\n" +
                "FROM activated_user u\n" +
                "WHERE u.email = ?;";
        return jdbcTemplate.query(sql, new Object[] {email}, userWithPasswordMapper).stream().findFirst();
    }

    @Override
    public Optional<UserDao> findById(long userId)
    {
        var sql = "SELECT u.id,\n" +
                "u.forename,\n" +
                "u.surname,\n" +
                "u.email,\n" +
                "u.phone_number,\n" +
                "u.role,\n" +
                "u.activated,\n" +
                "u.disabled,\n" +
                "u.created_at,\n" +
                "u.updated_at\n" +
                "FROM enabled_user u\n" +
                "WHERE u.id = ?;";
        return jdbcTemplate.query(sql, new Object[] {userId}, userMapper).stream().findFirst();
    }

    @Override
    public Optional<UserDao> findByIdIncDisabled(long userId)
    {
        var sql = "SELECT u.id,\n" +
                "u.forename,\n" +
                "u.surname,\n" +
                "u.email,\n" +
                "u.phone_number,\n" +
                "u.role,\n" +
                "u.activated,\n" +
                "u.disabled,\n" +
                "u.created_at,\n" +
                "u.updated_at\n" +
                "FROM user u\n" +
                "WHERE u.id = ?;";
        return jdbcTemplate.query(sql, new Object[] {userId}, userMapper).stream().findFirst();
    }

    @Override
    public Optional<UserDao> findByEmail(String email)
    {
        var sql = "SELECT u.id,\n" +
                "u.forename,\n" +
                "u.surname,\n" +
                "u.email,\n" +
                "u.phone_number,\n" +
                "u.role,\n" +
                "u.activated,\n" +
                "u.disabled,\n" +
                "u.created_at,\n" +
                "u.updated_at\n" +
                "FROM enabled_user u\n" +
                "WHERE u.email = ?;";
        return jdbcTemplate.query(sql, new Object[] {email}, userMapper).stream().findFirst();
    }

    @Override
    public boolean verifyActivationToken(String email, String token)
    {
        var sql = "UPDATE enabled_user SET activated = TRUE, activation_token = NULL WHERE email = ? AND activation_token = ?;";
        var rowsAffected = jdbcTemplate.update(sql, email, token);
        return rowsAffected == 1;
    }

    @Override
    public boolean disable(long userId)
    {
        var sql = "UPDATE user SET disabled = TRUE WHERE id = ?;";
        var rowsAffected = jdbcTemplate.update(sql, userId);
        return rowsAffected == 1;
    }

    @Override
    public boolean enable(long userId)
    {
        var sql = "UPDATE user SET disabled = FALSE WHERE id = ?;";
        var rowsAffected = jdbcTemplate.update(sql, userId);
        return rowsAffected == 1;
    }

    @Override
    public String generateActivationToken(String email)
    {
        var generateTokenSql = "UPDATE user SET activation_token = md5(CONCAT(NOW(), RAND())), activated = FALSE WHERE email = ?;";

        var retrieveTokenSql = "SELECT activation_token FROM user WHERE email = ?";

        jdbcTemplate.update(generateTokenSql, email);

        return jdbcTemplate.queryForObject(retrieveTokenSql, new Object[] {email}, String.class);
    }

    @Override
    public boolean delete(long userId)
    {
        var sql = "DELETE FROM user WHERE id = ?";
        var rowsAffected = jdbcTemplate.update(sql, userId);
        return rowsAffected == 1;
    }
}
