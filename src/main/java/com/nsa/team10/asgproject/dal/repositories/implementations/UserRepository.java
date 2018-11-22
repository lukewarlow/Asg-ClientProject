package com.nsa.team10.asgproject.dal.repositories.implementations;

import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.daos.UserWithPasswordDao;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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

    public void register(UserDao newUser, String hashedPassword)
    {
        var sql = "INSERT INTO user (forename, surname, email, phone_number, role, password) VALUES(?,?,?,?,?,?);";
        jdbcTemplate.update(sql, newUser.getForename(), newUser.getSurname(), newUser.getEmail(), newUser.getPhoneNumber(), newUser.getRole().ordinal(), hashedPassword);
    }

    public Optional<String> getPasswordByEmail(String email)
    {
        var sql = "SELECT password FROM user WHERE email = ?;";
        return jdbcTemplate.query(sql, new Object[] {email}, (rs, i) -> rs.getString("password")).stream().findFirst();
    }

    public Optional<UserWithPasswordDao> getUserWithEmail(String email)
    {
        var sql = "SELECT * FROM user WHERE email = ?;";
        return jdbcTemplate.query(sql, new Object[] {email}, userWithPasswordMapper).stream().findFirst();
    }
}
