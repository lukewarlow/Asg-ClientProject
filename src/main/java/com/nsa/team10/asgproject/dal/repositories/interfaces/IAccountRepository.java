package com.nsa.team10.asgproject.dal.repositories.interfaces;

import com.nsa.team10.asgproject.dal.daos.UserDao;

import java.util.Optional;

public interface IAccountRepository
{
    void register(UserDao newUser, String hashedPassword);

    Optional<String> getPasswordByEmail(String email);
}
