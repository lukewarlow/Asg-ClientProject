package com.nsa.team10.asgproject.dal.repositories.interfaces;

import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.daos.UserWithPasswordDao;

import java.util.Optional;

public interface IUserRepository
{
    void register(UserDao newUser, String hashedPassword);
    Optional<String> getPasswordByEmail(String email);
    Optional<UserWithPasswordDao> getUserWithEmail(String email);
}
