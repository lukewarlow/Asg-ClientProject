package com.nsa.team10.asgproject.dal.repositories.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.daos.UserWithPasswordDao;
import com.nsa.team10.asgproject.validation.ConflictException;

import java.util.Optional;

public interface IUserRepository
{
    void register(UserDao newUser, String hashedPassword) throws ConflictException;

    PaginatedList<UserDao> findAll(FilteredPageRequest pageRequest);
    PaginatedList<UserDao> findAllDisabled(FilteredPageRequest pageRequest);
    Optional<UserWithPasswordDao> findWithPasswordByEmail(String email);
    Optional<UserDao> findById(long userId);
    Optional<UserDao> findByIdIncDisabled(long userId);
    boolean disable(long userId);
    boolean enable(long userId);
    boolean delete(long userId);
}
