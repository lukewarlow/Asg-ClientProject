package com.nsa.team10.asgproject.dal.repositories.implementations;

import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository implements IAccountRepository
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void register(UserDao newUser, String hashedPassword)
    {
        var sql = "INSERT INTO user (forename, surname, email, phone_number, role, password) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, newUser.getForename(), newUser.getSurname(), newUser.getEmail(), newUser.getPhoneNumber(), newUser.getRole().ordinal(), hashedPassword);
    }
}
