package com.nsa.team10.asgproject.dal.repositories.interfaces;

import com.nsa.team10.asgproject.dal.daos.UserDao;

public interface IAccountRepository
{
    void register(UserDao newUser, String hashedPassword);
}
