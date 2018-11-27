package com.nsa.team10.asgproject.dal.repositories.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.daos.UserWithPasswordDao;
import com.nsa.team10.asgproject.validation.UserConflictException;

import java.util.List;
import java.util.Optional;

public interface IUserRepository
{
    void register(UserDao newUser, String hashedPassword) throws UserConflictException;

    PaginatedList<UserDao> findAll(FilteredPageRequest pageRequest);
    PaginatedList<UserDao> findAllDisabled(FilteredPageRequest pageRequest);

    Optional<UserWithPasswordDao> getUserWithPasswordByEmail(String email);
}
