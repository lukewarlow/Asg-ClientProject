package com.nsa.team10.asgproject.dal.repositories.implementations;

import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.daos.UserWithPasswordDao;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IUserRepository;
import com.nsa.team10.asgproject.validation.UserConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository
{
    private final JdbcTemplate jdbcTemplate;

    private static RowMapper<UserWithPasswordDao> userWithPasswordMapper;

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
            rs.getString("password")
        );
    }

    public void register(UserDao newUser, String hashedPassword) throws UserConflictException
    {
        var sql = "INSERT INTO user (forename, surname, email, phone_number, role, password) VALUES(?,?,?,?,?,?);";
        try
        {
            jdbcTemplate.update(sql, newUser.getForename(), newUser.getSurname(), newUser.getEmail(), newUser.getPhoneNumber(), newUser.getRole().ordinal(), hashedPassword);
        }
        catch (DataAccessException ex)
        {
          if(ex.getLocalizedMessage().contains("for key 'email'"))
            throw new UserConflictException("Email address already in use.");
          if(ex.getLocalizedMessage().contains("for key 'phone_number'"))
            throw new UserConflictException("Phone number already in use.");
          else throw ex;
        }
    }

    public Optional<UserWithPasswordDao> getUserWithPasswordByEmail(String email)
    {
        var sql = "SELECT * FROM user WHERE email = ?;";
        return jdbcTemplate.query(sql, new Object[] {email}, userWithPasswordMapper).stream().findFirst();
    }
}
