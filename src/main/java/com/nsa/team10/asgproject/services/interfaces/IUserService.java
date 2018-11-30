package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.dal.daos.UserDao;

import java.util.Optional;

public interface IUserService
{
    PaginatedList<UserDao> findAll(FilteredPageRequest pageRequest);
    PaginatedList<UserDao> findAllDisabled(FilteredPageRequest pageRequest);
    Optional<UserDao> findById(long userId);
    Optional<UserDao> findByIdIncDisabled(long userId);
    boolean disable(long userId);
    boolean enable(long userId);
    boolean delete(long userId);
}
